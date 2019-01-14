package org.lyg.db.reflection;
import java.util.concurrent.ConcurrentHashMap;
@SuppressWarnings("unused")
public class Base{
	public ConcurrentHashMap<String, Table> getTables() {
		return tables;
	}

	public void setTables(ConcurrentHashMap<String, Table> tables) {
		this.tables = tables;
	}

	private ConcurrentHashMap<String, Table> tables;
}