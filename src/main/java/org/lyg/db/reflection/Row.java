package org.lyg.db.reflection;
import java.util.concurrent.ConcurrentHashMap;
@SuppressWarnings("unused")
public class Row{
	public ConcurrentHashMap<String, Cell> getCells() {
		return cells;
	}

	public void setCells(ConcurrentHashMap<String, Cell> cells) {
		this.cells = cells;
	}

	private ConcurrentHashMap<String, Cell> cells;
}