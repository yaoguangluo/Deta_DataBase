package org.lyg.vpc.process.portImpl;

import java.util.HashMap;
import java.util.Map;

import org.deta.vpcs.hall.DatabaseLogHall;
import org.json.JSONObject;
import org.lyg.common.utils.StringUtil;
import org.lyg.db.plsql.imp.ExecPLSQLImp;
import org.lyg.vpc.process.companyImpl.LoginServiceImpl;
 
public class RestDBPLSQLImpl {
	public static Map<String, Object> restDBPLSQLImpl(String token,
			String email, String password, String auth, String plsql) throws Exception{
		Map<String, Object> output = new HashMap<String, Object>();
		String who = "";
		//security monitor
		if(token != null && !token.equalsIgnoreCase("")){
			String checkStatus = LoginServiceImpl.checkTokenStatus(token, "common");
			if(checkStatus.contains("invalid")&&(auth.contains("1"))) {
				output.put("loginInfo", "unsuccess");
				output.put("returnResult", checkStatus);
				return output;
			}
			String json = StringUtil.decode(token);
			JSONObject js = new JSONObject(json);
			who = js.getString("uEmail");
		}else if(email != null && !email.equalsIgnoreCase("")){
			String checkStatus = LoginServiceImpl.checkRightsStatus(email, password, "DB");
			if(checkStatus.contains("invalid")) {
				output.put("loginInfo", "unsuccess");
				output.put("returnResult", checkStatus);
				return output;
			}
			who = email;
		}else{
			output.put("loginInfo", "unsuccess");
			output.put("returnResult", "invalid request");
			return output;
		}
		//write monior
		if(plsql.contains("update")||plsql.contains("insert")||plsql.contains("delete")
				||plsql.contains("drop")||plsql.contains("change")||plsql.contains("create")) {
			DatabaseLogHall.writeLogFile(System.currentTimeMillis(), who, plsql);
		}
		output = ExecPLSQLImp.ExecPLSQL(plsql);
		return output;
	}
}