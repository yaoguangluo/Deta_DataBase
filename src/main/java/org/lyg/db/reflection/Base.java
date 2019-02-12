package org.lyg.db.reflection;
import java.util.concurrent.ConcurrentHashMap;
public class Base{
	public ConcurrentHashMap<String, Table> getTables() {
		return tables;
	}

	public void setTables(ConcurrentHashMap<String, Table> tables) {
		this.tables = tables;
	}

	public Table getTable(String tableName) {
		if(tables.containsKey(tableName)) {
			return tables.get(tableName);
		}
		return null;	
	}

	public void putTable(String tableName, Table table) {
		this.tables.put(tableName, table);
	}

	private ConcurrentHashMap<String, Table> tables;
}