package org.lyg.vpc.process.portImpl;

import java.util.HashMap;
import java.util.Map;
import org.lyg.db.plsql.imp.ExecPLSQLImp;
import org.lyg.vpc.process.companyImpl.LoginServiceImpl;
 
public class RestDBPLSQLImpl {
	public static Map<String, Object> restDBPLSQLImpl(String token,
			String email, String password, String auth, String plsql) throws Exception{
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
		output = ExecPLSQLImp.ExecPLSQL(plsql);
		return output;
	}
}