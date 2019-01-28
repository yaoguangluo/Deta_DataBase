package org.deta.boot.vpc.vision;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.deta.boot.rest.VPC;
import org.lyg.cache.DetaCacheManager;
import org.lyg.common.utils.GzipUtil;

public class RestMapVision {
	public static void main(String[] args){

	}

	public static void getResponse(Socket socket) {

	}

	public static void returnResponse(Socket socket) {

	}

	public static void getResponse(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) {

	}

	public static void returnResponse(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) {
		vPCSResponse.getSleeperHall().removeThreadById(vPCSResponse.getHashCode());
	}

	public static void processRest(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws Exception {
		String output = VPC.forward(vPCSRequest.getRequestLink(), vPCSRequest.getRequestValue());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(vPCSResponse.getSocket()
				.getOutputStream(),"UTF-8")),true);
		pw.println("HTTP/1.1 200 OK\n"); 
		pw.println("Host:deta software  \n\n"); 
		output=output.charAt(0)=='"'?output.substring(1,output.length()):output;
		output=output.charAt(output.length()-1)=='"'?output.substring(0
				, output.length()-1):output;
		pw.println(output.replace("\\\"","\""));
		pw.flush();
		pw.close();	
		vPCSResponse.getSleeperHall().removeThreadById(vPCSResponse.getSocket().hashCode());
	}

	public static void processView(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) {

	}

	public static void processBytes(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws IOException {
		List<byte[]> list;
		DataOutputStream dataOutputStream = new DataOutputStream(vPCSResponse.getSocket().getOutputStream());
		if(DetaCacheManager.getCacheOfBytesList(vPCSRequest.getRequestFilePath()) != null){
			list = DetaCacheManager.getCacheOfBytesList(vPCSRequest.getRequestFilePath());
		}else{
			FileInputStream fileInputStream = new FileInputStream(new File(vPCSRequest.getRequestFilePath()));
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] byteArray = new byte[1024];
			int len = 0;
			list = new ArrayList<>();
			//这段while函数思想来自 这篇文章：https://blog.csdn.net/top_code/article/details/41042413
			//非常轻松处理len的长度溢出。谢谢。
			while((len = fileInputStream.read(byteArray, 0, 1024))!=-1){
				byteArrayOutputStream.write(byteArray, 0, len);
			}
			fileInputStream.close();
			byte[] sniper = GzipUtil.compress(byteArrayOutputStream.toByteArray());
			list.add(0, vPCSResponse.getResponseContentType().getBytes("UTF8"));
			list.add(0, ("Content-Length: " + sniper.length + " \n").getBytes("UTF8"));
			list.add(0, ("Cache-control: max-age=315360000 \n").getBytes("UTF8"));
			list.add(0, ("Content-Encoding:Gzip \n").getBytes("UTF8"));
			list.add(0, "Accept-Ranges: bytes \n".getBytes("UTF8"));
			list.add(0, "Host:deta software  \n".getBytes("UTF8"));
			list.add(0, "http/1.1 200 ok \n".getBytes("UTF8"));
			if(null != sniper && sniper.length>0) {
				list.add(sniper);
			}
			DetaCacheManager.putCacheOfBytesList(vPCSRequest.getRequestFilePath(), list);
		}	
		Iterator<byte[]> iterator = list.iterator();
		while(iterator.hasNext()){
			dataOutputStream.write(iterator.next());	
		}	
		dataOutputStream.flush();
		dataOutputStream.close();
	}

	public static void processBuffer(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws IOException {
		String builderToString;
		if(DetaCacheManager.getCacheOfString(vPCSRequest.getRequestFilePath()) != null){
			builderToString = DetaCacheManager.getCacheOfString(vPCSRequest.getRequestFilePath());
		}else{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("http/1.1 200 ok").append("\n");
			stringBuilder.append("Host:deta software  \n");
			stringBuilder.append("Cache-control: max-age=315360000 \n");
			stringBuilder.append(vPCSResponse.getResponseContentType());
			FileInputStream fileInputStream = new FileInputStream(new File(vPCSRequest.getRequestFilePath())); 
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, vPCSRequest.getRequestFileCode()); 
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader); 
			String line = null; 
			while ((line = bufferedReader.readLine()) != null) { 
				stringBuilder.append(line); 
			}
			bufferedReader.close();
			builderToString = stringBuilder.toString();
			DetaCacheManager.putCacheOfString(vPCSRequest.getRequestFilePath(), builderToString);
		}
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(vPCSResponse.getSocket().getOutputStream()
				, vPCSRequest.getRequestFileCode()));
		bufferedWriter.write(builderToString);
		bufferedWriter.flush();
		bufferedWriter.close();	
	}

	public static void processBufferBytes(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws UnsupportedEncodingException, IOException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("http/1.1 200 ok").append("\n");
		stringBuilder.append("Host:deta software  \n");
		stringBuilder.append("Cache-control: max-age=315360000 \n");
		stringBuilder.append("Content-Encoding:gzip \n");
		stringBuilder.append(vPCSResponse.getResponseContentType());
		String builderToString = stringBuilder.toString();
		String contentBuilderToString;
		if(DetaCacheManager.getCacheOfString(vPCSRequest.getRequestFilePath()) != null){
			contentBuilderToString = DetaCacheManager.getCacheOfString(vPCSRequest.getRequestFilePath());
		}else{
			StringBuilder contentBuilder = new StringBuilder();
			FileInputStream fileInputStream = new FileInputStream(new File(vPCSRequest.getRequestFilePath()));
			int len = 0;
			byte[] byteArray = new byte[1024];
			while ((len = fileInputStream.read(byteArray))!=-1){
				contentBuilder.append(new String(byteArray, 0, len,"UTF-8"));
			}
			fileInputStream.close();
			contentBuilderToString = contentBuilder.toString();
			DetaCacheManager.putCacheOfString(vPCSRequest.getRequestFilePath(), contentBuilderToString);
		}	
		DataOutputStream dataOutputStream = new DataOutputStream(vPCSResponse.getSocket().getOutputStream());
		dataOutputStream.write(builderToString.getBytes("UTF8"));
		dataOutputStream.write(GzipUtil.compress(contentBuilderToString.getBytes("UTF8")));
		dataOutputStream.flush();
		dataOutputStream.close();
	}
}