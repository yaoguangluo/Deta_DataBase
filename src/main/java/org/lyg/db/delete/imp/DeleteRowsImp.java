package org.lyg.db.delete.imp;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.lyg.cache.CacheManager;
import org.lyg.cache.DetaDBBufferCacheManager;
import org.lyg.db.reflection.Table;
import org.lyg.db.select.imp.SelectRowsImp;

public class DeleteRowsImp {
	public static Map<String, Object> deleteRowByTablePathAndIndex(String tablePath, String pageIndex, boolean mod)
			throws FileNotFoundException, IOException {
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
					String needCreatCulumnPath0 = DBTableRowIndexPath + "/is_delete_0";
					File needCreatCulumn0 = new File(needCreatCulumnPath0);
					if(mod) {
						needCreatCulumn0.delete();		
					}		
					String needCreatCulumnPath = DBTableRowIndexPath + "/is_delete_1";
					File needCreatCulumn = new File(needCreatCulumnPath);
					if(mod) {
						needCreatCulumn.mkdir();
					}
				}
			}
		}
		Map<String, Object> output = new HashMap<>();
		output.put("totalPages", rowInsertIndex);
		String[] sets = tablePath.split("/");
		String baseName = sets[sets.length-2];
		String tableName =  sets[sets.length-1];
		String indexName = "row"+pageIndex;
		Table table = DetaDBBufferCacheManager.db.getBase(baseName).getTable(tableName);
		if(mod) {
			table.removeRow(indexName);
		}
		return output;
	}

	@SuppressWarnings({"unchecked"})
	public static void deleteRowByAttributesOfCondition(Map<String, Object> object, boolean mod) throws IOException {
		if(!object.containsKey("baseName")||!object.containsKey("tableName")){
			return;
		}
		//get base
		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString() + "/" + object.get("baseName").toString();
		File DBPathFile = new File(DBPath);
		if(!DBPathFile.isDirectory()) {
			return;
		}
		//make table dir
		String tablePath = DBPath + "/" + object.get("tableName").toString();
		List<Map<String, Object>> obj = (List<Map<String, Object>>) SelectRowsImp.selectRowsByAttributesOfCondition(object);
		Iterator<Map<String, Object>> iterator = obj.iterator();
		while(iterator.hasNext()) {
			Map<String, Object> row = iterator.next();
			Map<String, Object> rowValue = (Map<String, Object>) row.get("rowValue");
			Map<String, Object> indexCell = (Map<String, Object>) rowValue.get("Index");
			String indexValue = indexCell.get("culumnValue").toString();
			deleteRowByTablePathAndIndex(tablePath, indexValue, mod);
			//delete buffer also
			//			DetaDBBufferCacheManager.db.getBase(object.get("baseName").toString()).getTable(object.get("tableName")
			//					.toString()).removeRow(indexValue);
		}
	}
}
	