package org.lyg.db.plsql.imp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@SuppressWarnings({"unused"})
public class ProcessAggregationPLSQL {
	public static void processAggregationLimitMap(String[] sets, List<Map<String, Object>> output) {
		List<Map<String, Object>> outputTemp = new ArrayList<>();
		Iterator<Map<String, Object>> iterator = output.iterator();
		int count = 0;
		while(iterator.hasNext()) {
			int rowid = count++;
			Map<String, Object> row = iterator.next();
			Map<String, Object> rowMap = new HashMap<>();
			if(sets[1].equalsIgnoreCase("~")) {
				if(rowid >= new BigDecimal(sets[0]).doubleValue() && rowid <= new BigDecimal(sets[2]).doubleValue()) {
					outputTemp.add(row);
				}	
			}
		}
		output.clear();
		output.addAll(outputTemp);
	}
}