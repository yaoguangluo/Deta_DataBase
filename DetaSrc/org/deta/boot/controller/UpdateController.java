package org.deta.boot.controller;
import java.util.Map;
import org.lyg.common.maps.VtoV;
import org.lyg.vpc.process.portImpl.RestDBUpdateImpl;
public class UpdateController {
	public static String exec(String string, Map<String, String> data) throws Exception {
		if(string.equalsIgnoreCase("/selectRowsByAttribute")){
			return new VtoV().ObjectToJsonString(RestDBUpdateImpl.updateRowByTablePathAndIndex(data.get("tablePath"), data.get("pageIndex")
					, data.get("culumnOfUpdateRow"), data.get("token"), data.get("email"), data.get("password"), data.get("auth")));
		}
		return "";
	}
}