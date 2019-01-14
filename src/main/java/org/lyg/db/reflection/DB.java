package org.lyg.db.reflection;
import java.util.concurrent.ConcurrentHashMap;
public class DB{
	@SuppressWarnings("unused")
	private ConcurrentHashMap<String, Base> bases;

	public ConcurrentHashMap<String, Base> getBases() {
		return bases;
	}

	public void setBases(ConcurrentHashMap<String, Base> bases) {
		this.bases = bases;
	}
}