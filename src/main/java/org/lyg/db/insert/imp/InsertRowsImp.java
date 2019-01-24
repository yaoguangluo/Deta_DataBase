package org.lyg.db.insert.imp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.lyg.cache.CacheManager;

@SuppressWarnings("unchecked")
public class InsertRowsImp {
	public static Map<String, Object> insertRowByTablePathAndIndex(String tablePath, String pageIndex, JSONObject culumnOfNewRow) throws FileNotFoundException, IOException {
		int rowInsertIndex = Integer.valueOf(pageIndex);
		File fileDBTable = new File(tablePath);
		if (fileDBTable.isDirectory()) {
			String DBTableRowsPath = tablePath + "/rows";	
			File fileDBTableRowsPath = new File(DBTableRowsPath);
			if (fileDBTableRowsPath.isDirectory()) {
				String DBTableRowIndexPath = DBTableRowsPath + "/row" + rowInsertIndex ;	
				File readDBTableRowIndexFile = new File(DBTableRowIndexPath);
				if (!readDBTableRowIndexFile.exists()) {
					readDBTableRowIndexFile.mkdir();
					Iterator<String> it = culumnOfNewRow.keys();
					while(it.hasNext()) {
						String culumnName = it.next();
						String culumnValue = culumnOfNewRow.getString(culumnName);
						String needCreatCulumnPath = DBTableRowIndexPath + "/" + culumnName;
						File needCreatCulumn = new File(needCreatCulumnPath);
						needCreatCulumn.mkdir();
						FileWriter fw = null;
						try {
							fw = new FileWriter(needCreatCulumnPath + "/value.lyg", true);
							fw.write(null==culumnValue?"":culumnValue);
							fw.close();
						} catch (IOException e) {
						}
					}
					String needCreatCulumnPath = DBTableRowIndexPath + "/is_delete_0";
					File needCreatCulumn = new File(needCreatCulumnPath);
					needCreatCulumn.mkdir();
				}
			}
		}
		Map<String, Object> output = new HashMap<>();
		output.put("totalPages", rowInsertIndex);
		return output;
	}

	public static Map<String, Object> insertRowByBaseName(String baseName, String tableName, JSONObject jsobj) {
		Map<String, Object> output = new HashMap<>();
		String tablePath = CacheManager.getCacheInfo("DBPath").getValue().toString();
		tablePath += "/" + baseName + "/" + tableName;
		File fileDBTable = new File(tablePath);
		if (fileDBTable.isDirectory()) {
			String DBTableRowsPath = tablePath + "/rows";	
			File fileDBTableRowsPath = new File(DBTableRowsPath);
			if (fileDBTableRowsPath.isDirectory()) {
				int rowInsertIndex = fileDBTableRowsPath.list().length;
				output.put("totalPages", rowInsertIndex);
				String DBTableRowIndexPath = DBTableRowsPath + "/row" + rowInsertIndex ;	
				File readDBTableRowIndexFile = new File(DBTableRowIndexPath);
				if (!readDBTableRowIndexFile.exists()) {
					readDBTableRowIndexFile.mkdir();
					Iterator<String> it = jsobj.keys();
					while(it.hasNext()) {
						String culumnName = it.next();
						String culumnValue = jsobj.get(culumnName).toString();
						if(culumnValue.equalsIgnoreCase("random")){
							culumnValue = "" + rowInsertIndex;
						}
						String needCreatCulumnPath = DBTableRowIndexPath + "/" + culumnName;
						File needCreatCulumn = new File(needCreatCulumnPath);
						needCreatCulumn.mkdir();
						FileWriter fw = null;
						try {
							fw = new FileWriter(needCreatCulumnPath + "/value.lyg", true);
							fw.write(null == culumnValue?"":culumnValue);
							fw.close();
						} catch (IOException e) {
						}
					}
					String needCreatCulumnPath = DBTableRowIndexPath + "/is_delete_0";
					File needCreatCulumn = new File(needCreatCulumnPath);
					needCreatCulumn.mkdir();
				}
			}
		}
		return output;
	}

	public static void insertRowByAttributes(Map<String, Object> object) {
		JSONObject jsobj = new JSONObject();
		//for late will make an exception record queue system, to control all of the db write;
		List<String[]> culumnValues = (List<String[]>)object.get("culumnValue");
		Iterator<String[]> iterator = culumnValues.iterator();
		//list to json
		while(iterator.hasNext()) {
			String[] strings = iterator.next();
			jsobj.put(strings[1], strings[2]);
		}	
		insertRowByBaseName(object.get("baseName").toString(), object.get("tableName").toString(), jsobj);
	}
}
	