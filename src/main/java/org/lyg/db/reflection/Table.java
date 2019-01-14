package org.lyg.db.reflection;
import java.util.concurrent.ConcurrentHashMap;
@SuppressWarnings("unused")
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
	private Spec spec;
	private ConcurrentHashMap<String, Row> rows;
}