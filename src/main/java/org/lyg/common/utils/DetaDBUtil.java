package org.lyg.common.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class DetaDBUtil {
	public static Map<String, Boolean> culumnType;
	public static String backEndRequest(String request) throws IOException {
		URL url = new URL("http://localhost:8080/" + request);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()),"UTF-8"));
		String out = "";
		String out1;
		while ((out1 = br.readLine()) != null) {
			out += out1;
		}
		conn.disconnect();
		return out;
	}

	public static String cacheRequest(String request) throws IOException {
		URL url = new URL("http://localhost:6379/" + request);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json;charset=utf-8");
		conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()),"UTF-8"));
		String out = "";
		String out1;
		while ((out1 = br.readLine()) != null) {
			out += out1;
		}
		conn.disconnect();
		return out;
	}

	public static void initCulumnNameType() {
		culumnType = new ConcurrentHashMap<>();
		culumnType.put("int", true);
		culumnType.put("long", true);
		culumnType.put("double", true);
		culumnType.put("string", true);
		culumnType.put("objectJPG", true);
		culumnType.put("objectPDF", true);
		culumnType.put("objectPNG", true);
		culumnType.put("objectMP4", true);
		culumnType.put("objectAVI", true);
		culumnType.put("objectGIF", true);
		culumnType.put("objectGIF", true);
	}

	public static boolean withoutCulumnNameType(String culumnTypeString) {
		if(!culumnType.containsKey(culumnTypeString)) {
			return true;	
		}
		return false;
	}
}