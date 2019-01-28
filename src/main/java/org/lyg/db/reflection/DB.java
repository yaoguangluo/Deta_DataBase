package org.lyg.db.reflection;
import java.util.concurrent.ConcurrentHashMap;
public class DB{
	private ConcurrentHashMap<String, Base> bases;

	public ConcurrentHashMap<String, Base> getBases() {
		return bases;
	}

	public void setBases(ConcurrentHashMap<String, Base> bases) {
		this.bases = bases;
	}
	
	public Base getBase(String baseName) {
		if(bases.containsKey(baseName)) {
			return bases.get(baseName);
		}
		return null;	
	}
	
	public void putBase(String baseName, Base base) {
		this.bases.put(baseName, base);
	}
}