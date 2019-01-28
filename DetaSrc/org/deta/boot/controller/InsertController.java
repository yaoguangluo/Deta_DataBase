package org.deta.boot.controller;
import java.util.Map;
import org.lyg.common.maps.VtoV;
import org.lyg.stable.StableData;
import org.lyg.vpc.process.portImpl.RestDBInsertImpl;
public class InsertController {
	public static String exec(String string, Map<String, String> data) throws Exception {
		if(string.equalsIgnoreCase(StableData.REST_PATH_INSERT_ROW_BY_BASE_NAME)){
			return VtoV.ObjectToJsonString(RestDBInsertImpl.insertRowByBaseName(data.get("baseName")
					, data.get("tableName"), data.get("culumnOfNewRow"), data.get("token"), data.get("email")
					, data.get("password"), data.get("auth")));
		}
		if(string.equalsIgnoreCase(StableData.REST_PATH_INSERT_ROW_BY_TABLE_PATH)){
			return VtoV.ObjectToJsonString(RestDBInsertImpl.insertRowByTablePath(data.get("tablePath")
					, data.get("pageIndex"), data.get("culumnOfNewRow"), data.get("token"), data.get("email")
					, data.get("password"), data.get("auth")));
		}
		return StableData.STRING_EMPTY;
	}
}