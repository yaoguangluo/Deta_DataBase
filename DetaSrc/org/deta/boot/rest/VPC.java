package org.deta.boot.rest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import org.deta.boot.controller.ConfigController;
import org.deta.boot.controller.DBCategoryController;
import org.deta.boot.controller.DeleteController;
import org.deta.boot.controller.InsertController;
import org.deta.boot.controller.SelectController;
import org.deta.boot.controller.UpdateController;
import org.lyg.common.maps.VtoV;
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
		//restMap
		if(string.equalsIgnoreCase("/login")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.login(data.get("uEmail"),data.get("uPassword")));	
		}
		if(string.equalsIgnoreCase("/find")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.find(data.get("uEmail")));
		}
		if(string.equalsIgnoreCase("/logout")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.logout(data.get("uEmail"), data.get("uToken")));
		}
		if(string.equalsIgnoreCase("/register")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.register(data.get("uEmail"), data.get("uEmailEnsure")
					, data.get("uName"), data.get("uPassword"), data.get("uPassWDEnsure"), data.get("uAddress")
					, data.get("uPhone"), data.get("uWeChat"), data.get("uQq"), data.get("uAge"), data.get("uSex")));	
		}
		if(string.equalsIgnoreCase("/change")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.change(data.get("uEmail"), data.get("uChange")
					, data.get("uChangeEnsure"),data.get("uToken"), data.get("uPassword")));	
		}
		if(string.equalsIgnoreCase("/checkStatus")){
			return new VtoV().ObjectToJsonString(RestLoginPortImpl.checkStatus(data.get("token")));	
		}
		return "";
	}
	//借鉴了http://tool.oschina.net/commons 的协议栈
	@SuppressWarnings("resource")
	public static StringBuilder buildWriteString(File file, String code, String filePath) throws IOException{
		if(filePath.contains(".js") && code.equalsIgnoreCase("UTF-8")){
			FileInputStream fis = new FileInputStream(file);
			int len = 0;
			byte[] byteArray = new byte[1024];
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("http/1.1 200 ok").append("\n\n");
			while ((len = fis.read(byteArray))!=-1){
				stringBuilder.append(new String(byteArray, 0, len));
			}
			return stringBuilder;
		}
		StringBuilder stringBuilder = new StringBuilder();
		if(filePath.contains(".css")){
			stringBuilder.append("http/1.1 200 ok").append("\n");
			stringBuilder.append("Content-Type: text/css").append("\r\n\r\n");
		}
		if(filePath.contains(".html")){
			stringBuilder.append("http/1.1 200 ok").append("\n");
			stringBuilder.append("Content-Type: text/html").append("\r\n\r\n");
		}
		FileInputStream fileInputStream = new FileInputStream(file); 
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, code); 
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader); 
		String line = null; 
		while ((line = bufferedReader.readLine()) != null) { 
			stringBuilder.append(line); 
		}
		return stringBuilder;
	}
	
	public static String getCode(String filePath) throws IOException{
		if(filePath.contains(".html")||filePath.contains(".js")){
			return "UTF-8";
		}
		return "GBK";
	}

	public static StringBuilder forwardLink(String filePath, Map<String, String> data, String code) throws IOException {
		return buildWriteString(new File(filePath), code, filePath);
	}

	public static String getFilePath(String string) {
		String root = new File("src/main/resources/static").getAbsolutePath().replace("\\", "/");
		if(string.equalsIgnoreCase("")||string.equalsIgnoreCase("/")){
			string = "/index.html";
		}
		return root + string;
	}
}