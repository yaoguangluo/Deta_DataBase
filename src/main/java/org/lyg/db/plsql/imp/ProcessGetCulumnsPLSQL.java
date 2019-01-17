package org.lyg.db.plsql.imp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@SuppressWarnings({"unused"})
public class ProcessGetCulumnsPLSQL {
	public static Object getCulumnsMapWithAs(String[] sets, Map<String, Object> row) {
		return row.get(sets[2]);	
	}

	public static Object getCulumnsMap(String[] sets,  Map<String, Object> row) {
		return row.get(sets[0]);		
	}

	public static Object processGetCulumnsMap(List<Map<String, Object>> obj, String[] getCulumnsValueArray) {
		List<Map<String, Object>> newobj = new ArrayList<Map<String, Object>>();
		Iterator<Map<String, Object>> iterator = obj.iterator(); 
		int count = 0;
		NextRow:
			while(iterator.hasNext()) {
				int rowId = count ++;
				Map<String, Object> row = iterator.next();
				Map<String, Object> newRow = new HashMap<>();
				NextCell:
					for(int i = 1; i < getCulumnsValueArray.length; i++) {
						String[] sets = getCulumnsValueArray[i].split("\\|");
						if(null == sets) {
							continue NextCell;
						}
						if(1 == sets.length) {
							newRow.put(sets[0], row.get(sets[0]));
							continue NextCell;
						}
						if(3 == sets.length && sets[1].equalsIgnoreCase("as") ) {
							newRow.put(sets[2], row.get(sets[0]));
							continue NextCell;
						}
					}
				newobj.add(newRow);
			}
		obj.clear();
		return obj.addAll(newobj);
	}
}
//	i' m tin god