package org.deta.boot.controller;
import java.util.Map;
import org.lyg.common.maps.VtoV;
import org.lyg.vpc.process.portImpl.RestDBSelectImpl;
public class SelectController {
	public static String exec(String string, Map<String, String> data) throws Exception {
		if(string.equalsIgnoreCase("/selectRowsByAttribute")){
			return new VtoV().ObjectToJsonString(RestDBSelectImpl.selectRowsByAttribute(data.get("baseName")
					, data.get("tableName"), data.get("culumnName"),data.get("value"), data.get("token")
					, data.get("email"), data.get("password"), data.get("auth")));	
		}
		
		if(string.equalsIgnoreCase("/selectRowsByTablePath")){
			return new VtoV().ObjectToJsonString(RestDBSelectImpl.selectRowsByTablePath(data.get("tablePath")
					, data.get("pageBegin"), data.get("pageEnd"), data.get("direction"), data.get("token")
					, data.get("email"), data.get("password"), data.get("auth")));
		}
		return "";
	}
}