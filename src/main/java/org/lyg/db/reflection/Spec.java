package org.lyg.db.reflection;
import java.util.concurrent.ConcurrentHashMap;
@SuppressWarnings("unused")
public class Spec{
	public ConcurrentHashMap<String, String> getCulumnNames() {
		return culumnNames;
	}
	public void setCulumnNames(ConcurrentHashMap<String, String> culumnNames) {
		this.culumnNames = culumnNames;
	}
	public ConcurrentHashMap<String, String> getCulumnTypes() {
		return culumnTypes;
	}
	public void setCulumnTypes(ConcurrentHashMap<String, String> culumnTypes) {
		this.culumnTypes = culumnTypes;
	}
	public ConcurrentHashMap<String, String> getCulumnCharset() {
		return culumnCharset;
	}
	public void setCulumnCharset(ConcurrentHashMap<String, String> culumnCharset) {
		this.culumnCharset = culumnCharset;
	}
	private ConcurrentHashMap<String, String> culumnNames;
	private ConcurrentHashMap<String, String> culumnTypes;
	private ConcurrentHashMap<String, String> culumnCharset;
}