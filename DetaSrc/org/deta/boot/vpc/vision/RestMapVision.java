package org.deta.boot.vpc.vision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

public class RestMapVision {
	public static void main(String[] args){

	}

	public static void getResponse(Socket socket) {
		// TODO Auto-generated method stub

	}

	public static void returnResponse(Socket socket) {
		// TODO Auto-generated method stub

	}

	public static void getResponse(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) {
		// TODO Auto-generated method stub

	}

	public static void returnResponse(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) {
		// TODO Auto-generated method stub

	}

	public static void processRest(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws Exception {
		String output = VPC.forward(vPCSRequest.getRequestLink(), vPCSRequest.getRequestValue());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(vPCSResponse.getSocket()
				.getOutputStream(),"UTF-8")),true);
		pw.println("HTTP/1.1 200 OK\n\n"); 
		output=output.charAt(0)=='"'?output.substring(1,output.length()):output;
		output=output.charAt(output.length()-1)=='"'?output.substring(0
				, output.length()-1):output;
		pw.println(output.replace("\\\"","\""));
		pw.flush();
		pw.close();	
		vPCSResponse.getSleeperHall().removeThreadById(vPCSResponse.getSocket().hashCode());
	}

	public static void processView(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) {
		// TODO Auto-generated method stub

	}

	public static void processBytes(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws IOException {
		List<byte[]> list;
		DataOutputStream dataOutputStream = new DataOutputStream(vPCSResponse.getSocket().getOutputStream());
		if(DetaCacheManager.getCacheOfBytesList(vPCSRequest.getRequestFilePath()) != null){
			list = DetaCacheManager.getCacheOfBytesList(vPCSRequest.getRequestFilePath());
		}else{
			FileInputStream fileInputStream = new FileInputStream(new File(vPCSRequest.getRequestFilePath()));
			byte[] byteArray = new byte[1024];
			int tlen = 0;
			int len = 0;
			list= new ArrayList<>();
			while((len = fileInputStream.read(byteArray)) > 0){
				tlen += len; 
				if(len != byteArray.length){
					byte[] newByteArray = new byte[len];
					list.add(newByteArray.clone());
				}
				list.add(byteArray.clone());
			}	
			fileInputStream.close();
			list.add(0, (vPCSResponse.getResponseContentType().getBytes()));
			list.add(0, ("Content-Length: " + tlen + " \n").getBytes());
			list.add(0, "Accept-Ranges: bytes \n".getBytes());
			list.add(0, "http/1.1 200 ok \n".getBytes());
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
		String builderToString;
		if(DetaCacheManager.getCacheOfString(vPCSRequest.getRequestFilePath()) != null){
			builderToString = DetaCacheManager.getCacheOfString(vPCSRequest.getRequestFilePath());
		}else{
			FileInputStream fileInputStream = new FileInputStream(new File(vPCSRequest.getRequestFilePath()));
			int len = 0;
			byte[] byteArray = new byte[1024];
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("http/1.1 200 ok").append("\n\n");
			while ((len = fileInputStream.read(byteArray))!=-1){
				stringBuilder.append(new String(byteArray, 0, len));
			}
			fileInputStream.close();
			builderToString = stringBuilder.toString();
			DetaCacheManager.putCacheOfString(vPCSRequest.getRequestFilePath(), builderToString);
		}
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(vPCSResponse.getSocket().getOutputStream()
				, vPCSRequest.getRequestFileCode()));
		bufferedWriter.write(builderToString);
		bufferedWriter.flush();
		bufferedWriter.close();	
	}
}