package org.deta.boot.rest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.deta.boot.controller.ConfigController;
import org.deta.boot.controller.DBCategoryController;
import org.deta.boot.controller.DeleteController;
import org.deta.boot.controller.InsertController;
import org.deta.boot.controller.SelectController;
import org.deta.boot.controller.UpdateController;
import org.lyg.common.maps.VtoV;
import org.lyg.stable.StableData;
import org.lyg.vpc.process.portImpl.RestDBPLSQLImpl;
import org.lyg.vpc.process.portImpl.RestLoginPortImpl;
public class VPC {
	public static String forward(String string, Map<String, String> data) throws Exception {
		//controller
		if(string.contains(StableData.REST_PATH_SELECT)){
			return SelectController.exec(string, data);	
		}
		if(string.contains(StableData.REST_PATH_SETDB)){
			return ConfigController.exec(string, data);	
		}
		if(string.contains(StableData.REST_PATH_INSERT)){
			return InsertController.exec(string, data);	
		}
		if(string.contains(StableData.REST_PATH_DELETE)){
			return DeleteController.exec(string, data);	
		}
		if(string.contains(StableData.REST_PATH_UPDATE)){
			return UpdateController.exec(string, data);	
		}
		if(string.contains(StableData.REST_PATH_DB_CATEGORY)){
			return DBCategoryController.exec(string, data);	
		}
		//plsql
		if(string.equalsIgnoreCase(StableData.REST_PATH_EXEC_DETA_PLSQL)){
			return VtoV.ObjectToJsonString(RestDBPLSQLImpl.restDBPLSQLImpl(data.get("token") ,data.get("email")
					, data.get("password"), data.get("auth"), data.get("LYGQuery")));
		}
		//restMap
		if(string.equalsIgnoreCase(StableData.REST_PATH_LOGIN)){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.login(data.get("uEmail"),data.get("uPassword")));	
		}
		if(string.equalsIgnoreCase(StableData.REST_PATH_FIND)){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.find(data.get("uEmail")));
		}
		if(string.equalsIgnoreCase(StableData.REST_PATH_LOGOUT)){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.logout(data.get("uEmail"), data.get("uToken")));
		}
		if(string.equalsIgnoreCase(StableData.REST_PATH_REGISTER)){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.register(data.get("uEmail"), data.get("uEmailEnsure")
					, data.get("uName"), data.get("uPassword"), data.get("uPassWDEnsure"), data.get("uAddress")
					, data.get("uPhone"), data.get("uWeChat"), data.get("uQq"), data.get("uAge"), data.get("uSex")));	
		}
		if(string.equalsIgnoreCase(StableData.REST_PATH_CHANGE)){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.change(data.get("uEmail"), data.get("uChange")
					, data.get("uChangeEnsure"),data.get("uToken"), data.get("uPassword")));	
		}
		if(string.equalsIgnoreCase(StableData.REST_PATH_CHECK_STATUS)){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.checkStatus(data.get("token")));	
		}
		return StableData.STRING_EMPTY;
	}

	public static String getCode(String filePath) throws IOException{
		if(filePath.contains(StableData.FILE_HTML)||filePath.contains(StableData.FILE_JS)){
			return "UTF-8";
		}
		return "GBK";
	}

	public static String getFilePath(String string) {
		String root = new File("src/main/resources/static").getAbsolutePath().replace("\\", "/");
		if(string.equalsIgnoreCase("")||string.equalsIgnoreCase("/")){
			string = "/index.html";
		}
		return root + string;
	}
}