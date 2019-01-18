package org.lyg.db.select.imp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.lyg.cache.CacheManager;
import org.lyg.cache.DetaDBBufferCacheManager;
import org.lyg.db.plsql.imp.ProcessAggregationPLSQL;
import org.lyg.db.plsql.imp.ProcessConditionPLSQL;
import org.lyg.db.plsql.imp.ProcessGetCulumnsPLSQL;
import org.lyg.db.plsql.imp.ProcessRelationPLSQL;
import org.lyg.db.reflection.Spec;
@SuppressWarnings({"unused", "unchecked"})
public class SelectJoinRowsImp {
	public static Object SelectRowsByAttributesOfJoinCondition(Map<String, Object> object) throws IOException {
		if(!object.containsKey("recordRows")) {
			Map<String, Boolean> recordRows = new ConcurrentHashMap<>();
			object.put("recordRows", recordRows);
		}
		Spec spec = new Spec();
		spec.setCulumnTypes(new ConcurrentHashMap<String, String>());
		String objectType = "";
		List<Map<String, Object>> output = new ArrayList<>();
		//锁定数据库
		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString() + "/" + object.get("joinBaseName").toString();
		//锁定表
		File fileDBPath = new File(DBPath);
		if (fileDBPath.isDirectory()) {
			String DBTablePath = DBPath + "/" + object.get("joinTableName").toString();
			File fileDBTable = new File(DBTablePath);
			if (fileDBTable.isDirectory()) {
				String DBTableCulumnPath = DBTablePath + "/spec";
				File fileDBTableCulumn = new File(DBTableCulumnPath);
				if (fileDBTableCulumn.isDirectory()) {
					//读取列数据格式
					String[] fileList = fileDBTableCulumn.list();
					for(int i=0; i<fileList.length; i++) {
						File readDBTableSpecCulumnFile = new File(DBTableCulumnPath + "/" + fileList[0] + "/value.lyg");
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
								ProcessConditionPLSQL.processCache(sets, output, object.get("joinTableName").toString()
										, object.get("joinBaseName").toString(), object);
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

	public static Object SelectRowsByAttributesOfJoinAggregation(Map<String, Object> object) {
		if(!object.containsKey("joinObj")) {
			return new ArrayList<>();
		}
		List<Map<String, Object>> obj = ((List<Map<String, Object>>)(object.get("obj")));
		List<String[]> aggregationValues = (List<String[]>) object.get("aggregation");
		Iterator<String[]> iterator = aggregationValues.iterator();
		while(iterator.hasNext()) {
			boolean overMap = obj.size() == 0? false: true;
			String[] aggregationValueArray = iterator.next();
			String type = aggregationValueArray[1];
			boolean limitMap = type.equalsIgnoreCase("limit")?true:false;
			for(int i = 2; i < aggregationValueArray.length; i++) {
				String[] sets = aggregationValueArray[i].split("\\|");
				if(limitMap) {
					ProcessAggregationPLSQL.processAggregationLimitMap(sets, obj);
				}
				//基于sort key 前序treeMap 之后排序功能设计
				//基于sort key 后序treeMap
			}
		}
		return obj;
	}

	public static Object SelectRowsByAttributesOfJoinGetCulumns(Map<String, Object> object) {
		if(!object.containsKey("joinObj")) {
			return new ArrayList<>();
		}
		List<Map<String, Object>> obj = ((List<Map<String, Object>>)(object.get("joinObj")));
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

	public static Object SelectRowsByAttributesOfJoinRelation(Map<String, Object> object) {
		if(!object.containsKey("obj")||!object.containsKey("joinObj")) {
			return new ArrayList<>();
		}
		Map<String,Boolean> findinNewObj = new HashMap<>();
		List<Map<String, Object>> newObj = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> obj = ((List<Map<String, Object>>)(object.get("obj")));
		List<Map<String, Object>> joinObj= ((List<Map<String, Object>>)(object.get("joinObj")));
		List<String[]> relationValues= (List<String[]>) object.get("relation");
		Iterator<String[]> iterator= relationValues.iterator();
		while(iterator.hasNext()) {
			boolean overObjMap= obj.size() == 0? false: true;
			boolean overJoinObjMap= joinObj.size() == 0? false: true;
			String[] getRelationValueArray = iterator.next();
			String type = getRelationValueArray[1];
			boolean andMap = type.equalsIgnoreCase("and")?true:false;
			for(int i= 2; i< getRelationValueArray.length; i++) {
				String[] sets = getRelationValueArray[i].split("\\|");
				if(overObjMap&& overJoinObjMap&&andMap && i>2) {
					ProcessRelationPLSQL.processAndMap(sets, obj, joinObj,object, newObj);
				}else {
					ProcessRelationPLSQL.processOrMap(sets, obj, joinObj, object, newObj, findinNewObj);
				}
			}
		}
		return newObj;
	}
}