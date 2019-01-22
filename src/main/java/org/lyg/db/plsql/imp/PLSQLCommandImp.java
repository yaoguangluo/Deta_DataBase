package org.lyg.db.plsql.imp;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.lyg.cache.Cache;
import org.lyg.cache.CacheManager;
import org.lyg.cache.DetaDBBufferCacheManager;
import org.lyg.db.create.imp.CreateTablesImp;
import org.lyg.db.insert.imp.InsertRowsImp;
import org.lyg.db.select.imp.SelectJoinRowsImp;
import org.lyg.db.select.imp.SelectRowsImp;
import org.lyg.db.update.imp.UpdateRowsImp;
@SuppressWarnings("unchecked")
public class PLSQLCommandImp {
	public static void proceseSetRoot(String[] acknowledge, Map<String, Object> output) throws Exception {
		String dbPath = acknowledge[1];
		for(int i=2; i<acknowledge.length; i++) {
			dbPath += ":" + acknowledge[i];
		}
		if(null != CacheManager.getCacheInfo("DBPath")) {
			File file = new File(dbPath);
			if(!file.exists()) {
				file.mkdirs();
				Cache c = new Cache();
				c.setValue(dbPath);
				CacheManager.putCache("DBPath", c);
			}else if(file.isFile()) {
				throw new Exception();
			}else if(file.isDirectory()) {
				Cache c = new Cache();
				c.setValue(dbPath);
				CacheManager.putCache("DBPath", c);
			}
		}
	}

	public static void processBaseName(String[] acknowledge, Map<String, Object> object) {
		object.put(acknowledge[0], acknowledge[1]);
	}

	public static void processTableName(String[] acknowledge, Map<String, Object> object) {
		object.put(acknowledge[0], acknowledge[1]);
		object.put("type", acknowledge[2]);
	}

	public static void processListNeedStart(String[] acknowledge, Map<String, Object> object) {
		object.put("start", "1");
		if(object.containsKey(acknowledge[0])) {
			List<String[]> relationValues = (List<String[]>) object.get(acknowledge[0]);
			relationValues.add(acknowledge);
			object.put(acknowledge[0], relationValues);
			return;
		}
		List<String[]> relationValues = new CopyOnWriteArrayList<>();
		relationValues.add(acknowledge);
		object.put(acknowledge[0], relationValues);
	}

	public static void processJoin(String[] acknowledge, Map<String, Object> object) {
		object.put("joinBaseName", acknowledge[1]);
		object.put("joinTableName", acknowledge[2]);
	}

	public static void processExec(String[] acknowledge, Map<String, Object> object) throws Exception {
		if(object.get("start").toString().equals("1")) {
			if(!acknowledge[0].equalsIgnoreCase(object.get("lastCommand").toString())
					&&(object.get("lastCommand").toString().contains("changeCulumnName")
							||object.get("lastCommand").toString().contains("culumnValue")
							||object.get("lastCommand").toString().contains("condition")
							||object.get("lastCommand").toString().contains("relation")
							||object.get("lastCommand").toString().contains("aggregation")
							||object.get("lastCommand").toString().contains("getCulumns")
							||object.get("lastCommand").toString().contains("culumnName")
							||object.get("lastCommand").toString().contains("relation"))) {
				processExecKernel(object);
			}
		}
	}

	private static void processExecKernel(Map<String, Object> object) throws Exception{
		if(object.get("type").toString().equalsIgnoreCase("select") && !object.containsKey("joinBaseName")) {
			if(object.containsKey("condition")) {
				object.put("obj", SelectRowsImp.SelectRowsByAttributesOfCondition(object));
			}
			if(object.containsKey("aggregation")) {
				object.put("obj", SelectRowsImp.SelectRowsByAttributesOfAggregation(object));
			}
			if(object.containsKey("getCulumns")) {
				object.put("obj", SelectRowsImp.SelectRowsByAttributesOfGetCulumns(object));
			}
			object.remove("recordRows");
		}
		if(object.get("type").toString().equalsIgnoreCase("select") && object.containsKey("joinBaseName")){
			if(object.containsKey("condition")) {
				object.put("joinObj", SelectJoinRowsImp.SelectRowsByAttributesOfJoinCondition(object));
			}
			if(object.containsKey("relation")) {
				object.put("obj", SelectJoinRowsImp.SelectRowsByAttributesOfJoinRelation(object));
			}
			if(object.containsKey("aggregation")) {
				object.put("obj", SelectJoinRowsImp.SelectRowsByAttributesOfJoinAggregation(object));
			}
			if(object.containsKey("getCulumns")) {
				object.put("joinObj", SelectJoinRowsImp.SelectRowsByAttributesOfJoinGetCulumns(object));
			}
			object.remove("recordRows");
		}
		if(object.get("type").toString().equalsIgnoreCase("create")){
			if(object.containsKey("culumnName")) {
				CreateTablesImp.CreateTable(object);
			}
			object.remove("recordRows");
		}
		if(object.get("type").toString().equalsIgnoreCase("update")) {
			if(object.containsKey("condition")) {
				object.put("updateObj", SelectRowsImp.SelectRowsByAttributesOfCondition(object));
			}
			if(object.containsKey("culumnValue")) {
				UpdateRowsImp.UpdateRowsByRecordConditions(object);
			}
		}
		if(object.get("type").toString().equalsIgnoreCase("insert")) {
			if(object.containsKey("culumnValue")) {
				InsertRowsImp.InsertRowByAttributes(object);
			}
		}
		object.remove("condition");
		object.remove("culumnName");
		object.remove("changeCulumnName");
		object.remove("getCulumns");
		object.remove("relation");
		object.remove("aggregation");
		object.remove("getCulumns");
		object.put("start", "0");
	}

	public static void processCheck(String acknowledge, Map<String, Object> object) throws Exception {
		if(object.get("start").toString().equals("1")) {
			processExecKernel(object);
		}
		List<Map<String, Object>> obj = ((List<Map<String, Object>>)(object.get("obj")));
		int totalPages = 0;
		if(obj != null) {
			totalPages = obj.size();
		}
		int rowBeginIndex = object.containsKey("pageBegin")? Integer.valueOf(object.get("pageBegin").toString()):0 ;
		int rowEndIndex = object.containsKey("pageEnd")?Integer.valueOf(object.get("pageEnd").toString()):totalPages>15?15:totalPages;
		object.put("pageBegin", rowBeginIndex);
		object.put("pageEnd", rowEndIndex);

		String DBPath = CacheManager.getCacheInfo("DBPath").getValue().toString() + "/" + object.get("baseName").toString();
		String DBTablePath = DBPath + "/" + object.get("tableName").toString();
		object.put("tablePath", DBTablePath);
		object.put("returnResult", "success");
		object.put("totalPages",totalPages);
		object.put("loginInfo", "success");
		List<Object> spec = new ArrayList<>();
		Iterator<String> iterator;
		if(obj == null || obj.size() < 1) {
			iterator = DetaDBBufferCacheManager.db.getBase(object.get("baseName").toString()).getTable(object.get("tableName").toString())
					.getSpec().getCulumnTypes().keySet().iterator();
		}else {
			iterator = ((Map<String, Object>)obj.get(0).get("rowValue")).keySet().iterator();
		}
		while(iterator.hasNext()) {
			spec.add(iterator.next());
		}
		object.put("spec", spec);
	}
}