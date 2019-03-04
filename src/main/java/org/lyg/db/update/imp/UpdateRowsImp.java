package org.lyg.db.update.imp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;
import org.lyg.cache.CacheManager;
import org.lyg.cache.DetaDBBufferCacheManager;
import org.lyg.db.plsql.imp.ProcessAggregationPLSQL;
import org.lyg.db.plsql.imp.ProcessConditionPLSQL;
import org.lyg.db.plsql.imp.ProcessGetCulumnsPLSQL;
import org.lyg.db.reflection.Cell;
import org.lyg.db.reflection.Row;
import org.lyg.db.reflection.Spec;
@SuppressWarnings("unchecked")
public class UpdateRowsImp {
	public static Map<String, Object> updateRowByTablePathAndIndex(String tablePath, String pageIndex,
			JSONObject jaculumnOfUpdateRow) throws FileNotFoundException, IOException {
		String[] sets = tablePath.split("/");
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
							fw.write(null == culumnValue?"":culumnValue);
							fw.close();
							//fix buffer refresh
							Cell cell = new Cell();
							cell.setCellValue(null == culumnValue ? "" : culumnValue);
							DetaDBBufferCacheManager.db.getBase(sets[sets.length - 2])
							.getTable(sets[sets.length - 1]).getRow("row" + pageIndex).putCell(culumnName, cell);	
						} catch (IOException e) {
							e.printStackTrace();
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
		String[] sets = tablePath.split("/");
		File fileDBTable = new File(tablePath);
		if (fileDBTable.isDirectory()) {
			String DBTableRowsPath = tablePath + "/rows";	
			File fileDBTableRowsPath = new File(DBTableRowsPath);
			if (fileDBTableRowsPath.isDirectory()) {
				Here:
					for(int i = 0; i < fileDBTableRowsPath.list().length; i++) {
						String DBTableRowIndexPath = DBTableRowsPath + "/row" + i;	
						File readDBTableRowIndexFile = new File(DBTableRowIndexPath);
						if (readDBTableRowIndexFile.exists()) {
							readDBTableRowIndexFile.mkdir();
							File check = new File(DBTableRowIndexPath + "/" + culumnName + "/value.lyg");
							if(!check.exists()) {
								continue Here;	
							}
							BufferedReader reader = new BufferedReader(new FileReader(check));  
							String temp = "";
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
									//fix buffer refresh
									Cell cell = new Cell();
									cell.setCellValue(null == culumnValueOfjs ? "" : culumnValueOfjs);
									DetaDBBufferCacheManager.db.getBase(sets[sets.length - 2])
									.getTable(sets[sets.length - 1]).getRow("row" + i).putCell(culumnNameOfjs, cell);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
			}
		}
		Map<String, Object> output = new HashMap<>();
		return output;
	}

	public static Object updateRowsByRecordConditions(Map<String, Object> object, boolean mod) throws IOException {
		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString() + "/" + object.get("baseName").toString();
		String DBtablePath = DBPath + "/" + object.get("tableName").toString();
		String DBTableRowsPath = DBtablePath + "/rows";		
		List<Map<String, Object>> updateObj = (List<Map<String, Object>>)object.get("updateObj");
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
					if(mod) {
						currentCellChange.delete();
						FileWriter fw = null;
						try {
							fw = new FileWriter(filePath, true);
							fw.write(culumns[2]);
							fw.close();
						} catch (IOException e) {
							fw.close();
							e.printStackTrace();
						}
					}
					Cell cell = new Cell();
					cell.setCellValue(culumns[2]);
					Row row = DetaDBBufferCacheManager.db.getBase(object.get("baseName").toString())
							.getTable(object.get("tableName").toString()).getRow(rowIndex);
					if(mod) {
						row.putCell(culumns[1], cell);
					}
				}	
			}	
		}
		return object;
	}

	public static Object updateRowsByAttributesOfCondition(Map<String, Object> object, boolean mod) throws IOException {
		if(!object.containsKey("recordRows")) {
			Map<String, Boolean> recordRows = new ConcurrentHashMap<>();
			object.put("recordRows", recordRows);
		}
		Spec spec = new Spec();
		spec.setCulumnTypes(new ConcurrentHashMap<String, String>());
		String objectType = "";
		List<Map<String, Object>> output = new ArrayList<>();
		//锁定数据库
		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString() + "/" + object.get("baseName").toString();
		//锁定表
		File fileDBPath = new File(DBPath);
		if (fileDBPath.isDirectory()) {
			String DBTablePath = DBPath + "/" + object.get("tableName").toString();
			File fileDBTable = new File(DBTablePath);
			if (fileDBTable.isDirectory()) {
				String DBTableCulumnPath = DBTablePath + "/spec";
				File fileDBTableCulumn = new File(DBTableCulumnPath);
				if (fileDBTableCulumn.isDirectory()) {
					//读取列数据格式
					String[] fileList = fileDBTableCulumn.list();
					for(int i=0; i<fileList.length; i++) {
						File readDBTableSpecCulumnFile = new File(DBTableCulumnPath + "/" + fileList[0]+"/value.lyg");
						BufferedReader reader = new BufferedReader(new FileReader(readDBTableSpecCulumnFile));  
						String tempString = null;
						while ((tempString = reader.readLine()) != null) {  
							objectType = tempString;			
						}
						reader.close();
						spec.setCulumnType(fileList[i], objectType);
					}
					List<String[]> conditionValues = (List<String[]>) object.get("condition");
					Iterator<String[]> iterator = conditionValues.iterator();
					while(iterator.hasNext()) {
						boolean overMap = output.size() == 0? false: true;
						String[] conditionValueArray = iterator.next();
						String type = conditionValueArray[1];
						boolean andMap = type.equalsIgnoreCase("and")?true:false;
						for(int i = 2; i < conditionValueArray.length; i++) {
							String[] sets = conditionValueArray[i].split("\\|");
							if(overMap && andMap) {
								ProcessConditionPLSQL.processMap(sets, output, DBTablePath);
							}else if(DetaDBBufferCacheManager.dbCache){
								ProcessConditionPLSQL.processCache(sets, output, object.get("tableName").toString()
										, object.get("baseName").toString(), object);
							}else {
								ProcessConditionPLSQL.processTable(sets, output, DBTablePath, object);
							}
						}
					}
				}
			}
		}
		return output;
	}

	public static List<Map<String, Object>> updateRowsByAttributesOfAggregation(Map<String, Object> object, boolean mod) {
		if(!object.containsKey("obj")) {
			return new ArrayList<>();
		}
		List<Map<String, Object>> obj = ((List<Map<String, Object>>)(object.get("updateObj")));
		List<String[]> aggregationValues = (List<String[]>) object.get("aggregation");
		Iterator<String[]> iterator = aggregationValues.iterator();
		while(iterator.hasNext()) {
			//	boolean overMap = obj.size() == 0? false: true;
			String[] aggregationValueArray = iterator.next();
			String type = aggregationValueArray[1];
			boolean limitMap = type.equalsIgnoreCase("limit")?true:false;
			for(int i = 2; i < aggregationValueArray.length; i++) {
				String[] sets = aggregationValueArray[i].split("\\|");
				//String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString() + "/" + object.get("baseName").toString();
				//String dBTablePath = DBPath + "/" + object.get("tableName").toString();
				if(limitMap) {
					ProcessAggregationPLSQL.processAggregationLimitMap(sets, obj);
				}
				//基于sort key 前序treeMap 之后排序功能设计
				//基于sort key 后序treeMap
			}
		}
		return obj;
	}

	public static Object updateRowsByAttributesOfGetCulumns(Map<String, Object> object) {
		if(!object.containsKey("obj")) {
			return new ArrayList<>();
		}
		List<Map<String, Object>> obj = ((List<Map<String, Object>>)(object.get("updateObj")));
		List<String[]> getCulumnsValues = (List<String[]>) object.get("getCulumns");
		Iterator<String[]> iterator = getCulumnsValues.iterator();
		while(iterator.hasNext()) {
			boolean overMap = obj.size() == 0? false: true;
			String[] getCulumnsValueArray = iterator.next();
			if(overMap) {
				ProcessGetCulumnsPLSQL.processGetCulumnsMap(obj, getCulumnsValueArray);
			}
		}
		return obj;
	}
}
