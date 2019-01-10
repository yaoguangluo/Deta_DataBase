package org.lyg.cache;
import java.util.Date;
public class TimeCheck{
	public long before;
	public long now;
	public void begin(){
		System.out.println("start: " + (new Date()));
		before = System.currentTimeMillis();
	}
	public void end(){
		now = System.currentTimeMillis();
		System.out.println("time: " + duration() + " ms");
	}
	public long duration(){
		return now-before;
	}
}