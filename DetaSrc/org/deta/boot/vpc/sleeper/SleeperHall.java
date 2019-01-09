package org.deta.boot.vpc.sleeper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class SleeperHall{
	private Map<String, Sleeper> sleepersMap;
	public SleeperHall(){
		sleepersMap = new ConcurrentHashMap<>();
	}
	public int getThreadsCount() {
		return sleepersMap.size();
	}
	public void addExecSleeper(String sid, Sleeper sleeper) {
		sleepersMap.put(sid, sleeper);
	}
	public void removeThreadById(String sid) {
		if(sleepersMap.containsKey(sid)){
			sleepersMap.remove(sid);
		}
	}
}