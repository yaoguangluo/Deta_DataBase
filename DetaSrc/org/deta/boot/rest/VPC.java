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
import org.lyg.vpc.process.portImpl.RestDBPLSQLImpl;
import org.lyg.vpc.process.portImpl.RestLoginPortImpl;
public class VPC {
	public static String forward(String string, Map<String, String> data) throws Exception {
		//controller
		if(string.contains("/select")){
			return SelectController.exec(string, data);	
		}
		if(string.contains("/setDB")){
			return ConfigController.exec(string, data);	
		}
		if(string.contains("/insert")){
			return InsertController.exec(string, data);	
		}
		if(string.contains("/delete")){
			return DeleteController.exec(string, data);	
		}
		if(string.contains("/update")){
			return UpdateController.exec(string, data);	
		}
		if(string.contains("DBCategory")){
			return DBCategoryController.exec(string, data);	
		}
		//plsql
		if(string.equalsIgnoreCase("/execDetaPLSQL")){
			return VtoV.ObjectToJsonString(RestDBPLSQLImpl.restDBPLSQLImpl(data.get("token") ,data.get("email")
					, data.get("password"), data.get("auth"), data.get("LYGQuery")));
		}
		//restMap
		if(string.equalsIgnoreCase("/login")){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.login(data.get("uEmail"),data.get("uPassword")));	
		}
		if(string.equalsIgnoreCase("/find")){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.find(data.get("uEmail")));
		}
		if(string.equalsIgnoreCase("/logout")){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.logout(data.get("uEmail"), data.get("uToken")));
		}
		if(string.equalsIgnoreCase("/register")){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.register(data.get("uEmail"), data.get("uEmailEnsure")
					, data.get("uName"), data.get("uPassword"), data.get("uPassWDEnsure"), data.get("uAddress")
					, data.get("uPhone"), data.get("uWeChat"), data.get("uQq"), data.get("uAge"), data.get("uSex")));	
		}
		if(string.equalsIgnoreCase("/change")){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.change(data.get("uEmail"), data.get("uChange")
					, data.get("uChangeEnsure"),data.get("uToken"), data.get("uPassword")));	
		}
		if(string.equalsIgnoreCase("/checkStatus")){
			return VtoV.ObjectToJsonString(RestLoginPortImpl.checkStatus(data.get("token")));	
		}
		return "";
	}

	public static String getCode(String filePath) throws IOException{
		if(filePath.contains(".html")||filePath.contains(".js")){
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