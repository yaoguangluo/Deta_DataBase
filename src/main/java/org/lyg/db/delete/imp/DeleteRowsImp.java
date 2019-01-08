package org.lyg.db.delete.imp;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteRowsImp {
	public static Map<String, Object> deleteRowByTablePathAndIndex(String tablePath, String pageIndex)
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
					needCreatCulumn0.delete();			
					String needCreatCulumnPath = DBTableRowIndexPath + "/is_delete_1";
					File needCreatCulumn = new File(needCreatCulumnPath);
					needCreatCulumn.mkdir();
				}
			}
		}
		Map<String, Object> output = new HashMap<>();
		output.put("totalPages", rowInsertIndex);
		return output;
	}
}
	