package org.lyg.db.reflection;
import java.util.concurrent.ConcurrentHashMap;
public class Row{
	public ConcurrentHashMap<String, Cell> getCells() {
		return cells;
	}

	public void setCells(ConcurrentHashMap<String, Cell> cells) {
		this.cells = cells;
	}

	public Cell getCell(String cellName) {
		if(cells.containsKey(cellName)) {
			return cells.get(cellName);
		}
		return null;	
	}

	public void putCell(String cellName, Cell cell) {
		this.cells.put(cellName, cell);
	}

	private ConcurrentHashMap<String, Cell> cells;

	public boolean containsCell(String cellName) {
		if(cells.containsKey(cellName)) {
			return true;
		}
		return false;
	}

	public void removeCell(String cellName) {
		if(cells.containsKey(cellName)) {
			cells.remove(cellName);
		}
	}
}