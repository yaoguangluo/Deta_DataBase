package org.lyg.vpc.process.factoryImpl;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.lyg.cache.CacheManager;
import org.lyg.db.insert.imp.InsertRowsImp;
import org.lyg.db.select.imp.SelectRowsImp;
import org.lyg.db.update.imp.UpdateRowsImp;
import org.lyg.vpc.view.Usr;
import org.lyg.vpc.view.UsrFull;
import org.lyg.vpc.view.UsrToken;

public class LoginDAOImpl {
//	@Autowired
//	private SelectRows selectRows;
//	
//	@Autowired
//	private UpdateRows updateRows;
//	
//	@Autowired
//	private InsertRows insertRows;

	public static Usr selectUsrByUId(Integer uId) throws IOException {
		List<Map<String, Object>> list = SelectRowsImp.selectRowsByAttribute("backend", "usr", "u_id", "" + uId);	
		Usr usr = new Usr();
		if(list.size() > 0) {
			usr.setuAddress(list.get(0).get("u_address")!=null?list.get(0).get("u_address").toString():"");
			usr.setuAge(Integer.valueOf(list.get(0).get("u_age")!=null?list.get(0).get("u_age").toString():"0"));
			usr.setuClass(list.get(0).get("u_class")!=null?list.get(0).get("u_class").toString():"");
			usr.setuEmail(list.get(0).get("u_email").toString());
			usr.setuId(Integer.valueOf(list.get(0).get("u_id").toString()));
			usr.setuName(list.get(0).get("u_name").toString());
			usr.setuPhone(list.get(0).get("u_phone")!=null?list.get(0).get("u_phone").toString():"");
			usr.setuQq(list.get(0).get("u_qq")!=null?list.get(0).get("u_qq").toString():"");
			usr.setuSex(list.get(0).get("u_sex")!=null?list.get(0).get("u_sex").toString():"");
			usr.setuWeChat(list.get(0).get("u_weChat")!=null?list.get(0).get("u_weChat").toString():"");
		}
		return usr;
	}

	public static UsrToken selectUsrTokenByUId(Integer uId) throws IOException {
		List<Map<String, Object>> list = SelectRowsImp.selectRowsByAttribute("backend", "usrToken", "u_id", ""+uId);	
		UsrToken usrToken = new UsrToken();
		if(list.size() > 0) {	
			usrToken.setuId(Integer.valueOf(list.get(0).get("u_id").toString()));
			usrToken.setuKey(list.get(0).get("u_key")!=null?list.get(0).get("u_key").toString():"");
			usrToken.setuPassword(list.get(0).get("u_password").toString());
			usrToken.setuTime(Integer.valueOf(list.get(0).get("u_time")!=null?list.get(0).get("u_time").toString():"0"));
			usrToken.setuLevel(list.get(0).get("u_level")!=null?list.get(0).get("u_level").toString():"");
		}
		return usrToken;
	}

	public static UsrFull selectUsrFullByUId(Integer uId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Usr selectUsrByUEmail(String uEmail) throws IOException {
		List<Map<String, Object>> list = SelectRowsImp.selectRowsByAttribute("backend", "usr", "u_email", uEmail);	
		Usr usr = new Usr();
		if(list.size() > 0) {
			usr.setuAddress(list.get(0).get("u_address")!=null?list.get(0).get("u_address").toString():"");
			usr.setuAge(Integer.valueOf(list.get(0).get("u_age")!=null?list.get(0).get("u_age").toString():"0"));
			usr.setuClass(list.get(0).get("u_class")!=null?list.get(0).get("u_class").toString():"");
			usr.setuEmail(list.get(0).get("u_email").toString());
			usr.setuId(Integer.valueOf(list.get(0).get("u_id").toString()));
			usr.setuName(list.get(0).get("u_name").toString());
			usr.setuPhone(list.get(0).get("u_phone")!=null?list.get(0).get("u_phone").toString():"");
			usr.setuQq(list.get(0).get("u_qq")!=null?list.get(0).get("u_qq").toString():"");
			usr.setuSex(list.get(0).get("u_sex")!=null?list.get(0).get("u_sex").toString():"");
			usr.setuWeChat(list.get(0).get("u_weChat")!=null?list.get(0).get("u_weChat").toString():"");
			usr.setuPassword(list.get(0).get("u_password")!=null?list.get(0).get("u_password").toString():"");
		}
		return usr;
	}

	public static void updateUsrByUId(Integer uId, String uName, String uAge, String uSex, String uPhone, String uAddress,
			String uWeChat, String uClass, String uEmail, String uQq) throws IOException {
		JSONObject jobj = new JSONObject();
		jobj.put("u_id", uId);
		jobj.put("u_name", uName);
		jobj.put("u_age", uAge);
		jobj.put("u_sex", uSex);
		jobj.put("u_phone", uPhone);
		jobj.put("u_address", uAddress);
		jobj.put("u_weChat", uWeChat);
		jobj.put("u_class", uClass);
		jobj.put("u_email", uEmail);
		jobj.put("u_qq", uQq);
		UpdateRowsImp.updateRowByTablePathAndAttribute(CacheManager.getCacheInfo("DBPath").getValue()+"/backend/usr"
				, "u_id", "" + uId, jobj);
		// TODO Auto-generated method stub
	}

	public static void updateUsrTokenByUId(Integer uId, String uKey, String uPassword, long uTime) throws IOException {
		JSONObject jobj = new JSONObject();
		jobj.put("u_id", uId);
		jobj.put("u_key", uKey);
		jobj.put("u_password", uPassword);
		jobj.put("u_time", uTime);
		UpdateRowsImp.updateRowByTablePathAndAttribute(CacheManager.getCacheInfo("DBPath").getValue()+"/backend/usrToken"
				, "u_id", "" + uId, jobj);
		// TODO Auto-generated method stub	
	}

	public static void insertUsr(String uName, String uAge, String uSex, String uPhone, String uAddress, String uWeChat,
			String uClass, String uEmail, String uQq) {
		// TODO Auto-generated method stub
		
	}

	public static void insertUsroken(Integer uId, String uKey, String uPassword, long uTime) {
		// TODO Auto-generated method stub
		
	}

	public static void insertRowByTablePath(String baseName, String tableName, JSONObject jsobj) throws Exception {
		InsertRowsImp.insertRowByBaseName(baseName, tableName, jsobj, true);
	}
	
}