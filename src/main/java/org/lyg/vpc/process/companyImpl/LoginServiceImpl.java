package org.lyg.vpc.process.companyImpl;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import org.json.JSONObject;
import org.lyg.common.utils.StringUtil;
import org.lyg.common.utils.TokenUtil;
import org.lyg.vpc.process.factoryImpl.LoginDAOImpl;
import org.lyg.vpc.view.Usr;
import org.lyg.vpc.view.UsrToken;
public class LoginServiceImpl {

	//	@Autowired
	//	private LoginDAO loginDAO;

	public static Usr findUsrByUEmail(String uEmail) throws IOException {
		Usr usr = LoginDAOImpl.selectUsrByUEmail(uEmail);
		return usr;
	}

	public static UsrToken findUsrTokenByUId(Integer uId) throws IOException {
		UsrToken usrToken = LoginDAOImpl.selectUsrTokenByUId(uId);
		return usrToken;
	}

	public static void updateUsrTokenByUId(Integer uId, String key, String uPassword, long uTime) throws IOException {
		LoginDAOImpl.updateUsrTokenByUId(uId, key, uPassword, uTime);
	}

	public static void insertRowByTablePath(String baseName, String tableName, JSONObject jsobj) throws Exception {
		LoginDAOImpl.insertRowByTablePath(baseName, tableName, jsobj);
	}

	public static String checkTokenStatus(String token, String level) throws Exception {
		if (null == token) {
			return "invalid ÃØÔ¿¶ªÊ§ÇëÖØÐÂµÇÂ½¡£";
		}
		String json = StringUtil.decode(token);
		JSONObject js;
		try {
			js = new JSONObject(json);
		}catch(Exception e) {
			return "invalid ÃØÔ¿´íÎóÇëÖØÐÂµÇÂ½¡£";
		}
		long uTime = js.getLong("uTime");
		String uPassword = js.getString("mPassword");
		String uEmail = js.getString("uEmail");
		Usr usr = findUsrByUEmail(uEmail);
		UsrToken usrToken = findUsrTokenByUId(usr.getuId());
		String password = TokenUtil.getFirstMD5Password(js.getString("uKey"), usrToken.getuPassword());
		if (!uPassword.equals(password)) {
			return "invalid ÃÜÂë´íÎó¡£";
		}
		long nowTime = new Date().getTime();
		if(uTime + 600000 < nowTime) {
			return "invalid 10·ÖÖÓ³¬Ê±£¬ÇëÖØÐÂµÇÂ½¡£";
		}

		if(level.contains("level")) {
			String uLevel = usrToken.getuLevel();
			if(!uLevel.contains("high")) {
				return "invalid È¨ÏÞ²»¹»";
			}
		}
		return "valid";
	}

	public static String checkRightsStatus(String inEmail, String inPassword, String level) throws Exception {
		if (null == inEmail) {
			return "invalid ÃØÔ¿¶ªÊ§ÇëÖØÐÂµÇÂ½¡£";
		}
		//String uPassword = inPassword;
		String uEmail = inEmail;
		Usr usr = findUsrByUEmail(uEmail);
		//UsrToken usrToken = this.findUsrTokenByUId(usr.getuId());
		String password = TokenUtil.getSecondMD5Password(inPassword);
		if (!usr.getuPassword().equals(password)) {
			return "invalid ÃÜÂë´íÎó¡£";
		}
		return "valid";
	}

	public static String checkTokenStatusAndGetLevel(String token, String level, Map<String, Object> output) throws Exception {
		if (null == token || token.equalsIgnoreCase("undefined")) {
			return "invalid ÃØÔ¿¶ªÊ§ÇëÖØÐÂµÇÂ½¡£";
		}
		String json = StringUtil.decode(token);
		JSONObject js;
		try {
			js = new JSONObject(json);
		}catch(Exception e) {
			return "invalid ÃØÔ¿´íÎóÇëÖØÐÂµÇÂ½¡£";
		}
		long uTime = js.getLong("uTime");
		String uPassword = js.getString("mPassword");
		String uEmail = js.getString("uEmail");
		Usr usr = findUsrByUEmail(uEmail);
		UsrToken usrToken = findUsrTokenByUId(usr.getuId());
		String password = TokenUtil.getFirstMD5Password(js.getString("uKey"), usrToken.getuPassword());
		if (!uPassword.equals(password)) {
			return "invalid ÃÜÂë´íÎó¡£";
		}
		long nowTime = new Date().getTime();
		if(uTime + 600000 < nowTime) {
			return "invalid 10·ÖÖÓ³¬Ê±£¬ÇëÖØÐÂµÇÂ½¡£";
		}

		if(level.contains("level")) {
			String uLevel = usrToken.getuLevel();
			if(!uLevel.contains("high")) {
				return "invalid È¨ÏÞ²»¹»";
			}
		}
		output.put("usrName", "×ÉÑ¯×¨¼Ò" + usr.getuId());
		return "valid" + usrToken.getuLevel();
	}
}