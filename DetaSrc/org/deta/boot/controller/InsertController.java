package org.deta.boot.controller;
import java.util.Map;
import org.lyg.common.maps.VtoV;
import org.lyg.vpc.process.portImpl.RestDBInsertImpl;
public class InsertController {
	public static String exec(String string, Map<String, String> data) throws Exception {
		if(string.equalsIgnoreCase("/insertRowByBaseName")){
			return VtoV.ObjectToJsonString(RestDBInsertImpl.insertRowByBaseName(data.get("baseName")
					, data.get("tableName"), data.get("culumnOfNewRow"), data.get("token"), data.get("email")
					, data.get("password"), data.get("auth")));
		}
		if(string.equalsIgnoreCase("/insertRowByTablePath")){
			return VtoV.ObjectToJsonString(RestDBInsertImpl.insertRowByTablePath(data.get("tablePath")
					, data.get("pageIndex"), data.get("culumnOfNewRow"), data.get("token"), data.get("email")
					, data.get("password"), data.get("auth")));
		}
		return "";
	}
}