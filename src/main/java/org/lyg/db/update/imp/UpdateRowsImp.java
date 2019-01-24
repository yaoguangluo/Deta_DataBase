package org.lyg.db.update.imp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.lyg.cache.CacheManager;
import org.lyg.cache.DetaDBBufferCacheManager;
import org.lyg.db.reflection.Cell;
@SuppressWarnings("unchecked")
public class UpdateRowsImp {
	public static Map<String, Object> updateRowByTablePathAndIndex(String tablePath, String pageIndex,
			JSONObject jaculumnOfUpdateRow) throws FileNotFoundException, IOException {
		int rowInsertIndex = Integer.valueOf(pageIndex);
		File fileDBTable = new File(tablePath);
		if (fileDBTable.isDirectory()) {
			String DBTableRowsPath = tablePath + "/rows";	
			File fileDBTableRowsPath = new File(DBTableRowsPath);
			if (fileDBTableRowsPath.isDirectory()) {
				String DBTableRowIndexPath = DBTableRowsPath + "/row" + rowInsertIndex ;	
				File readDBTableRowIndexFile = new File(DBTableRowIndexPath);
				if (readDBTableRowIndexFile.exists()) {
					readDBTableRowIndexFile.mkdir();
					Iterator<String> it = jaculumnOfUpdateRow.keys();
					while(it.hasNext()) {
						String culumnName = it.next();
						String culumnValue = jaculumnOfUpdateRow.getString(culumnName);
						String needCreatCulumnPath = DBTableRowIndexPath + "/" + culumnName;
						File needCreatCulumn = new File(needCreatCulumnPath);
						needCreatCulumn.delete();
						needCreatCulumn.mkdir();
						File needCreatCulumnFile = new File(needCreatCulumnPath + "/value.lyg");
						needCreatCulumnFile.delete();
						FileWriter fw = null;
						try {
							fw = new FileWriter(needCreatCulumnPath + "/value.lyg", true);
							fw.write(null==culumnValue?"":culumnValue);
							fw.close();
						} catch (IOException e) {
						}
					}
				}
			}
		}
		Map<String, Object> output = new HashMap<>();
		output.put("totalPages", rowInsertIndex);
		return output;
	}

	public static Map<String, Object> updateRowByTablePathAndAttribute(String tablePath, String culumnName, String culumnValue,
			JSONObject jobj) throws IOException {
		File fileDBTable = new File(tablePath);
		if (fileDBTable.isDirectory()) {
			String DBTableRowsPath = tablePath + "/rows";	
			File fileDBTableRowsPath = new File(DBTableRowsPath);
			if (fileDBTableRowsPath.isDirectory()) {
				Here:
					for(int i = 0; i<fileDBTableRowsPath.list().length; i++) {
						String DBTableRowIndexPath = DBTableRowsPath + "/row" + i;	
						File readDBTableRowIndexFile = new File(DBTableRowIndexPath);
						if (readDBTableRowIndexFile.exists()) {
							readDBTableRowIndexFile.mkdir();
							File check = new File(DBTableRowIndexPath + "/" + culumnName + "/value.lyg");
							if(!check.exists()) {
								continue Here;	
							}
							BufferedReader reader = new BufferedReader(new FileReader(check));  
							String temp="";
							String tempString;
							while ((tempString = reader.readLine()) != null) {
								temp += tempString;
							}
							reader.close();
							if(!temp.equalsIgnoreCase(culumnValue)) {
								continue Here;
							}
							Iterator<String> it = jobj.keys();
							while(it.hasNext()) {
								String culumnNameOfjs = it.next();
								String culumnValueOfjs = jobj.get(culumnNameOfjs).toString();
								String needCreatCulumnPath = DBTableRowIndexPath + "/" + culumnNameOfjs;
								File needCreatCulumn = new File(needCreatCulumnPath);
								needCreatCulumn.delete();
								needCreatCulumn.mkdir();
								File needCreatCulumnFile = new File(needCreatCulumnPath + "/value.lyg");
								needCreatCulumnFile.delete();
								FileWriter fw = null;
								try {
									fw = new FileWriter(needCreatCulumnPath + "/value.lyg", true);
									fw.write(null == culumnValueOfjs? "" : culumnValueOfjs);
									fw.close();
								} catch (IOException e) {
								}
							}
						}
					}
			}
		}
		Map<String, Object> output = new HashMap<>();
		return output;
	}

	public static Object updateRowsByRecordConditions(Map<String, Object> object) throws IOException {
		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString() + "/" + object.get("baseName").toString();
		String DBtablePath = DBPath + "/" + object.get("tableName").toString();
		String DBTableRowsPath = DBtablePath + "/rows";		
		List<Map<String, Object>> updateObj =  (List<Map<String, Object>>) object.get("updateObj");
		Iterator<Map<String, Object>> updateObjIterator = updateObj.iterator();	
		List<String[]> culumnValues = (List<String[]>) object.get("culumnValue");
		Iterator<String[]> culumnValuesIterator = culumnValues.iterator();	
		while(updateObjIterator.hasNext()) {
			Map<String, Object> objRow = updateObjIterator.next();
			Map<String, Object> objCells = (Map<String, Object>) objRow.get("rowValue");
			Map<String, Object>	onjCell = (Map<String, Object>) objCells.get("Index");
			String rowIndex = "row";
			rowIndex += onjCell.get("culumnValue").toString();
			while(culumnValuesIterator.hasNext()) {
				String[] culumns = culumnValuesIterator.next();
				String filePath = DBTableRowsPath + "/" + rowIndex + "/" +culumns[1] + "/value.lyg";
				File currentCellChange = new File(filePath);
				if(currentCellChange.exists()) {
					currentCellChange.delete();
					FileWriter fw = null;
					try {
						fw = new FileWriter(filePath, true);
						fw.write(culumns[2]);
						fw.close();
						Cell cell = new Cell();
						cell.setCellValue(culumns[2]);
						DetaDBBufferCacheManager.db.getBase(object.get("baseName").toString())
						.getTable(object.get("tableName").toString()).getRow(rowIndex).putCell(culumns[1], cell);
					} catch (IOException e) {
						fw.close();
						e.printStackTrace();
					}
				}
			}
		}
		return object;
	}
}
