package org.deta.boot.controller;
import java.util.Map;
import org.lyg.common.maps.VtoV;
import org.lyg.stable.StableData;
import org.lyg.vpc.process.portImpl.RestControllerPortImpl;
public class DBCategoryController {
	public static String exec(String string, Map<String, String> data) throws Exception {
		if(string.equalsIgnoreCase(StableData.REST_GET_DB_CATEGORY)){
			return VtoV.ObjectToJsonString(RestControllerPortImpl.getDBCategory(data.get(StableData.DB_BASE_NAME)
					, data.get(StableData.LOGIN_TOKEN),data.get(StableData.LOGIN_AUTH)));	
		}
		if(string.equalsIgnoreCase(StableData.REST_GET_ALL_DB_CATEGORY)){
			return VtoV.ObjectToJsonString(RestControllerPortImpl.getAllDBCategory(data.get(StableData.LOGIN_TOKEN)
					, data.get(StableData.LOGIN_AUTH)));
		}
		return StableData.STRING_EMPTY;
	}
}