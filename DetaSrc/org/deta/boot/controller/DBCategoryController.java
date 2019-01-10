package org.deta.boot.controller;
import java.util.Map;
import org.lyg.common.maps.VtoV;
import org.lyg.vpc.process.portImpl.RestControllerPortImpl;
public class DBCategoryController {
	public static String exec(String string, Map<String, String> data) throws Exception {
		if(string.equalsIgnoreCase("/getDBCategory")){
			return VtoV.ObjectToJsonString(RestControllerPortImpl.getDBCategory(data.get("baseName")
					, data.get("token"),data.get("auth")));	
		}
		if(string.equalsIgnoreCase("/getAllDBCategory")){
			return VtoV.ObjectToJsonString(RestControllerPortImpl.getAllDBCategory(data.get("token"), data.get("auth")));
		}
		return "";
	}
}