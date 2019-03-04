package org.lyg.db.reflection;
import java.util.concurrent.ConcurrentHashMap;
public class Table{
	public Spec getSpec() {
		return spec;
	}
	
	public void setSpec(Spec spec) {
		this.spec = spec;
	}
	
	public ConcurrentHashMap<String, Row> getRows() {
		return rows;
	}
	
	public void setRows(ConcurrentHashMap<String, Row> rows) {
		this.rows = rows;
	}
	
	public Row getRow(String rowName) {
		if(rows.containsKey(rowName)) {
			return rows.get(rowName);
		}
		return null;	
	}

	public void putRow(String rowName, Row row) {
		this.rows.put(rowName, row);
	}
	
	private Spec spec;
	private ConcurrentHashMap<String, Row> rows;
	public void removeRow(String rowName) {
		if(rows.containsKey(rowName)) {
			rows.remove(rowName);
		}
	}
}