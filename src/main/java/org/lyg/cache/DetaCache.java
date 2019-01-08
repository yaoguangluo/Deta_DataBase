package org.lyg.cache;
public class DetaCache {

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}

	private String value;
	private Object object;
	private long timeOut;
	public DetaCache() {
		super();
	}

	public DetaCache(String value, Object object, long timeOut) {
		this.value = value;
		this.object = object;
		this.timeOut = timeOut;
	}
} 