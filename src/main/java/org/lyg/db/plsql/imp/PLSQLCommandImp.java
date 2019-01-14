package org.lyg.db.plsql.imp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lyg.db.select.imp.SelectRowsImp;
@SuppressWarnings("unchecked")
public class PLSQLCommandImp {
	public static void processBaseName(String[] acknowledge, Map<String, Object> object) {
		object.put(acknowledge[0], acknowledge[1]);
	}

	public static void processTableName(String[] acknowledge, Map<String, Object> object) {
		object.put("start", "1");
		object.put(acknowledge[0], acknowledge[1]);
		object.put("type", acknowledge[2]);
	}

	public static void processRelation(String[] acknowledge, Map<String, Object> object) {
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
		object.put("start", "1");
		object.put(acknowledge[0], acknowledge[1]);
	}

	public static void processCondition(String[] acknowledge, Map<String, Object> object) {
		if(object.containsKey(acknowledge[0])) {
			List<String[]> conditionValues = (List<String[]>) object.get(acknowledge[0]);
			conditionValues.add(acknowledge);
			object.put(acknowledge[0], conditionValues);
			return;
		}
		List<String[]> conditionValues = new CopyOnWriteArrayList<>();
		conditionValues.add(acknowledge);
		object.put(acknowledge[0], conditionValues);
	}

	public static void processCulumnValue(String[] acknowledge, Map<String, Object> object) {
		if(object.containsKey(acknowledge[0])) {
			List<String[]> culumnValues = (List<String[]>) object.get(acknowledge[0]);
			culumnValues.add(acknowledge);
			object.put(acknowledge[0], culumnValues);
			return;
		}
		List<String[]> culumnValues = new CopyOnWriteArrayList<>();
		culumnValues.add(acknowledge);
		object.put(acknowledge[0], culumnValues);
	}

	public static void processCulumnName(String[] acknowledge, Map<String, Object> object) {
		if(object.containsKey(acknowledge[0])) {
			List<String[]> culumnNames = (List<String[]>) object.get(acknowledge[0]);
			culumnNames.add(acknowledge);
			object.put(acknowledge[0], culumnNames);
			return;
		}
		List<String[]> culumnNames = new CopyOnWriteArrayList<>();
		culumnNames.add(acknowledge);
		object.put(acknowledge[0], culumnNames);
	}

	public static void processChangeCulumnName(String[] acknowledge, Map<String, Object> object) {
		if(object.containsKey(acknowledge[0])) {
			List<String[]> changeCulumnNames = (List<String[]>) object.get(acknowledge[0]);
			changeCulumnNames.add(acknowledge);
			object.put(acknowledge[0], changeCulumnNames);
			return;
		}
		List<String[]> changeCulumnNames = new CopyOnWriteArrayList<>();
		changeCulumnNames.add(acknowledge);
		object.put(acknowledge[0], changeCulumnNames);
	}

	public static void processExec(String[] acknowledge, Map<String, Object> object) {
		if(object.get("start").toString().equals("1")) {
			if(!acknowledge[0].equalsIgnoreCase(object.get("lastCommand").toString())) {
				if(object.get("type").toString().equalsIgnoreCase("select")) {
					object.put("obj", SelectRowsImp.SelectRowsByAttributes(object));
					object.remove("condition");
					object.remove("aggregate");
				}
				if(object.containsKey("join")){
					object.put("obj", SelectRowsImp.SelectRowsByJoinAttributes(object));
					object.remove("condition");
					object.remove("relation");
					object.remove("aggregate");
				}
				object.put("start", "0");
			}
		}
	}

	public static void processCheck(String[] acknowledge, Map<String, Object> object) {
		processExec(acknowledge, object);
		object.put("start", "0");
	}
}