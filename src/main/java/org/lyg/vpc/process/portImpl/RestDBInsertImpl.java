package org.lyg.vpc.process.portImpl;

import org.json.JSONObject;
import org.lyg.db.insert.imp.InsertRowsImp;
import org.lyg.vpc.process.companyImpl.LoginServiceImpl;
import java.util.HashMap;
import java.util.Map;

public class RestDBInsertImpl {
	public static Map<String, Object> insertRowByTablePath(String tablePath, String pageIndex
			, String culumnOfNewRow, String token, String email, String password, String auth) throws Exception {
		Map<String, Object> output = new HashMap<String, Object>();
		if(token != null && !token.equalsIgnoreCase("")){
			String checkStatus = LoginServiceImpl.checkTokenStatus(token, "common");
			if(checkStatus.contains("invalid")&&(auth.contains("1"))) {
				output.put("loginInfo", "unsuccess");
				output.put("returnResult", checkStatus);
				return output;
			}
		}else if(email != null && !email.equalsIgnoreCase("")){
			String checkStatus = LoginServiceImpl.checkRightsStatus(email, password, "DB");
			if(checkStatus.contains("invalid")) {
				output.put("loginInfo", "unsuccess");
				output.put("returnResult", checkStatus);
				return output;
			}
		}else{
			output.put("loginInfo", "unsuccess");
			output.put("returnResult", "invalid request");
			return output;
		}	

		JSONObject jaculumnOfNewRow=new JSONObject(culumnOfNewRow);
		output = InsertRowsImp.insertRowByTablePathAndIndex(tablePath, pageIndex, jaculumnOfNewRow);
		return output;
	}

	public static Map<String, Object> insertRowByBaseName(String baseName, String tableName, String culumnOfNewRow,
			String token, String email, String password, String auth) throws Exception {
		Map<String, Object> output = new HashMap<String, Object>();
		String checkStatus = LoginServiceImpl.checkRightsStatus(email, password, "DB");
		if(checkStatus.contains("invalid")) {
			output.put("loginInfo", "unsuccess");
			output.put("returnResult", checkStatus);
			return output;
		}

		checkStatus = LoginServiceImpl.checkTokenStatus(token, "common");
		if(checkStatus.contains("invalid")&&(auth.contains("1"))) {
			output.put("loginInfo", "unsuccess");
			output.put("returnResult", checkStatus);
			return output;
		}

		JSONObject jaculumnOfNewRow=new JSONObject(culumnOfNewRow);
		output = InsertRowsImp.insertRowByBaseName(baseName, tableName, jaculumnOfNewRow, true);
		return output;
	}
}