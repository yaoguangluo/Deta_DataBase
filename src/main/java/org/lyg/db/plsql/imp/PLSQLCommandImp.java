package org.lyg.db.plsql.imp;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
@SuppressWarnings("unchecked")
public class PLSQLCommandImp {
	public static void processBaseName(String[] acknowledge, Map<String, Object> object) {
		object.put(acknowledge[0], acknowledge[1]);
	}

	public static void processTableName(String[] acknowledge, Map<String, Object> object) {
		object.put(acknowledge[0], acknowledge[1]);
		object.put("type", acknowledge[2]);
	}

	public static void processRelation(String[] acknowledge, Map<String, Object> object) {
		object.put(acknowledge[0], acknowledge);
	}

	public static void processJoin(String[] acknowledge, Map<String, Object> object) {
		object.put(acknowledge[0], acknowledge[1]);
	}

	public static void processCondition(String[] acknowledge, Map<String, Object> object) {
		object.put(acknowledge[0], acknowledge);
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
		List<String[]> culumnValues = new CopyOnWriteArrayList<>();
		culumnValues.add(acknowledge);
		object.put(acknowledge[0], culumnValues);
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
}