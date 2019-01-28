package org.lyg.db.plsql.imp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@SuppressWarnings({"unused","unchecked"})
public class ProcessRelationPLSQL {
	public static void processAndMap(String[] sets, List<Map<String, Object>> obj, List<Map<String, Object>> joinObj
			, Map<String, Object> object, List<Map<String, Object>> newObj) {
		List<Map<String, Object>> newObjTemp = new ArrayList<>();
		Iterator<Map<String, Object>> iterator = newObj.iterator(); 
		int count = 0;
		while(iterator.hasNext()) {
			int objRowId = count++;
			Map<String, Object> objRow = iterator.next();
			if(objRow.containsKey(sets[0])&&objRow.containsKey(sets[2])) {
				if(sets[1].equalsIgnoreCase("==") || sets[1].equalsIgnoreCase("===")) {
					if(new BigDecimal(objRow.get(sets[0]).toString()).doubleValue() 
							== new BigDecimal(objRow.get(sets[2]).toString()).doubleValue()) {
						newObjTemp.add(objRow);
					}
				}
				if(sets[1].equalsIgnoreCase("!=") || sets[1].equalsIgnoreCase("=!") 
						|| sets[1].equalsIgnoreCase("<>") || sets[1].equalsIgnoreCase("><")) {
					if(new BigDecimal(objRow.get(sets[0]).toString()).doubleValue() 
							!= new BigDecimal(objRow.get(sets[2]).toString()).doubleValue()) {
						newObjTemp.add(objRow);
					}
				}
				if(sets[1].equalsIgnoreCase(">=") || sets[1].equalsIgnoreCase("=>")) {
					if(new BigDecimal(objRow.get(sets[0]).toString()).doubleValue() 
							>= new BigDecimal(objRow.get(sets[2]).toString()).doubleValue()) {
						newObjTemp.add(objRow);
					}
				}
				if(sets[1].equalsIgnoreCase(">")) {
					if(new BigDecimal(objRow.get(sets[0]).toString()).doubleValue() 
							> new BigDecimal(objRow.get(sets[2]).toString()).doubleValue()) {
						newObjTemp.add(objRow);
					}
				}
				if(sets[1].equalsIgnoreCase("<")) {
					if(new BigDecimal(objRow.get(sets[0]).toString()).doubleValue() 
							< new BigDecimal(objRow.get(sets[2]).toString()).doubleValue()) {
						newObjTemp.add(objRow);
					}
				}
				if(sets[1].equalsIgnoreCase("<=") || sets[1].equalsIgnoreCase("<=")) {
					if(new BigDecimal(objRow.get(sets[0]).toString()).doubleValue() 
							<= new BigDecimal(objRow.get(sets[2]).toString()).doubleValue()) {
						newObjTemp.add(objRow);
					}
				}
				if(sets[1].equalsIgnoreCase("equal")) {
					if(objRow.get(sets[0]).toString().equals(objRow.get(sets[2]).toString())){
						newObjTemp.add(objRow);
					}
				}
				if(sets[1].equalsIgnoreCase("!equal") || sets[1].equalsIgnoreCase("equal!")) {
					if(!objRow.get(sets[0]).toString().equals(objRow.get(sets[2]).toString())){
						newObjTemp.add(objRow);
					}
				}
				if(sets[1].equalsIgnoreCase("in")) {
					String set = "," + objRow.get(sets[2]).toString() + ",";
					if(set.contains(objRow.get(sets[0]).toString())){
						newObjTemp.add(objRow);
					}
				}
				if(sets[1].equalsIgnoreCase("!in")) {
					String set = "," + objRow.get(sets[2]).toString() + ",";
					if(!set.contains(objRow.get(sets[0]).toString())){
						newObjTemp.add(objRow);
					}
				}
			}
		}
	}

	public static void processOrMap(String[] sets, List<Map<String, Object>> obj, List<Map<String, Object>> joinObj
			, Map<String, Object> object, List<Map<String, Object>> newObj, Map<String, Boolean> findinNewObj) {
		Iterator<Map<String, Object>> iterator = obj.iterator(); 
		int count = 0;
		while(iterator.hasNext()) {
			int objRowId = count++;
			Map<String, Object> objRow = iterator.next();
			Map<String, Object> row = (Map<String, Object>) objRow.get("rowValue");
			Iterator<Map<String, Object>> iteratorJoin = joinObj.iterator(); 
			int countJoin = 0;
			while(iteratorJoin.hasNext()) {
				int objJoinRowId = countJoin++;
				Map<String, Object> objJoinRow = iteratorJoin.next();
				Map<String, Object> joinRow = (Map<String, Object>) objJoinRow.get("rowValue");
				Map<String, Object> cell = (Map<String, Object>) row.get(sets[0]);
				Map<String, Object> cellJoin = (Map<String, Object>) joinRow.get(sets[2]);
				if(sets[1].equalsIgnoreCase("==") || sets[1].equalsIgnoreCase("===")) {
					if(new BigDecimal(cell.get("culumnValue").toString()).doubleValue() 
							== new BigDecimal(cellJoin.get("culumnValue").toString()).doubleValue()) {
						if(!findinNewObj.containsKey(objRowId + ":" + objJoinRowId)) {
							Map<String, Object> newObjRow = new HashMap<>();
							Map<String, Object> newRow = new HashMap<>();
							newRow.putAll((Map<? extends String, ? extends Object>) objJoinRow.get("rowValue"));
							newRow.putAll((Map<? extends String, ? extends Object>) objRow.get("rowValue"));	 
							newObjRow.put("rowValue", newRow);
							newObj.add(newObjRow);
							findinNewObj.put(objRowId + ":" + objJoinRowId, true);
						} 
					}
				}
				if(sets[1].equalsIgnoreCase("equal")) {
					if(cell.get("culumnValue").toString().equals(cellJoin.get("culumnValue").toString())) {
						if(!findinNewObj.containsKey(objRowId + ":" + objJoinRowId)) {
							Map<String, Object> newObjRow = new HashMap<>();
							Map<String, Object> newRow = new HashMap<>();
							newRow.putAll((Map<? extends String, ? extends Object>) objJoinRow.get("rowValue"));
							newRow.putAll((Map<? extends String, ? extends Object>) objRow.get("rowValue"));	 
							newObjRow.put("rowValue", newRow);
							newObj.add(newObjRow);
							findinNewObj.put(objRowId + ":" + objJoinRowId, true);
						} 
					}
				}
			}
		}
	}
}