package org.deta.boot.vpc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.deta.boot.vpc.vision.VPCSRequest;
import org.deta.boot.vpc.vision.VPCSResponse;
import org.lyg.common.utils.DetaDBUtil;

@SuppressWarnings("unused")
public class RequestFilterController {
	static Map<String, Boolean> ipBlock;
	public static void main(String[] args){

	}

	public static void requestIpFilter(Socket socket) {
		// TODO Auto-generated method stub

	}

	public static void requestLinkFilter(Socket socket) {
		// TODO Auto-generated method stub

	}

	public static void requestIpFilter(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws IOException {
		//过滤block
		if(ipBlock.containsKey(vPCSRequest.getRequestIp())){
			vPCSResponse.returnErrorCode(403);
		}
		//同源csrf
		if(vPCSRequest.getRequestIp().equalsIgnoreCase(InetAddress.getLocalHost().getHostAddress())){
			vPCSResponse.returnErrorCode(405);
		}
	}

	public static void requestLinkFilter(VPCSRequest vpcsRequest, VPCSResponse vPCSResponse) throws IOException {
		//		//限制ddos
		//		String ipCount = DetaDBUtil.cacheRequest("get?key=" + vpcsRequest.getRequestIp() + "&email=" 
		//				+ "313699483@qq.com" + "&password=" + "Fengyue1985!");
		//
		//		if(null == ipCount){
		//			DetaDBUtil.cacheRequest("put?key=" + vpcsRequest.getRequestIp() + "&value=" + "1" + "&time=" 
		//					+ (2000+System.currentTimeMillis()) + "&email=" + "313699483@qq.com" + "&password=" + "Fengyue1985!");
		//		}else if(ipCount.contains("unsuccess")){
		//			DetaDBUtil.cacheRequest("put?key=" + vpcsRequest.getRequestIp() + "&value=" + "1" + "&time=" 
		//					+ (2000+System.currentTimeMillis()) + "&email=" + "313699483@qq.com" + "&password=" + "Fengyue1985!");
		//		}else if(Integer.valueOf(ipCount) <= 30){
		//			int digit = Integer.valueOf(ipCount) + 1;
		//			DetaDBUtil.cacheRequest("put?key=" + vpcsRequest.getRequestIp() + "&value=" + digit + "&time=" 
		//					+ 0 + "&email=" + "313699483@qq.com" + "&password=" + "Fengyue1985!");
		//		}else{
		//			vPCSResponse.returnErrorCode(400);
		//		}
	}

	public static void initBlockList() throws IOException {
		ipBlock = new ConcurrentHashMap<>();
		FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/ipBlock.ips")); 
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8"); 
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader); 
		String line = null; 
		while ((line = bufferedReader.readLine()) != null) { 
			ipBlock.put(line, true); 
		}
		bufferedReader.close();
	}
}