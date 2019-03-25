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
import org.lyg.stable.StableData;

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
				.getOutputStream(),StableData.CHARSET_UTF_8)),true);
		pw.println("HTTP/1.1 200 OK\n\n"); 
		output=output.charAt(StableData.INT_ZERO)=='"'?output.substring(StableData.INT_ONE,output.length())
				:output;
		output=output.charAt(output.length()-StableData.INT_ONE)=='"'?output.substring(StableData.INT_ZERO
				, output.length()-StableData.INT_ONE):output;
		pw.println(output.replace("\\\"","\""));
		System.out.println("db:"+4);
		pw.flush();
		pw.close();	
		vPCSResponse.getSleeperHall().removeThreadById(vPCSResponse.getSocket().hashCode());	
	}

	public static void processView(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) {

	}

	public static void processBytes(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws IOException {
		List<byte[]> list;
		DataOutputStream dataOutputStream = new DataOutputStream(vPCSResponse.getSocket().getOutputStream());
		if(null != DetaCacheManager.getCacheOfBytesList(vPCSRequest.getRequestFilePath())){
			list = DetaCacheManager.getCacheOfBytesList(vPCSRequest.getRequestFilePath());
		}else{
			FileInputStream fileInputStream = new FileInputStream(new File(vPCSRequest.getRequestFilePath()));
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] byteArray = new byte[StableData.BUFFER_RANGE_MAX];
			int lengthOfFile = StableData.INT_ZERO;
			list = new ArrayList<>();
			//这段while函数思想来自 这篇文章：https://blog.csdn.net/top_code/article/details/41042413
			//非常轻松处理len的长度溢出。谢谢。
			while((lengthOfFile = fileInputStream.read(byteArray, StableData.INT_ZERO
					, StableData.BUFFER_RANGE_MAX)) != StableData.INT_MINES_ONE){
				byteArrayOutputStream.write(byteArray, StableData.INT_ZERO, lengthOfFile);
			}
			fileInputStream.close();
			byte[] sniper = GzipUtil.compress(byteArrayOutputStream.toByteArray());
			list.add(0, vPCSResponse.getResponseContentType().getBytes(StableData.CHARSET_UTF8));
			list.add(0, (StableData.HEADER_CONTENT_LENGTH + sniper.length + StableData.STRING_SPACE_ENTER)
					.getBytes(StableData.CHARSET_UTF8));
			list.add(0, StableData.HEADER_CACHE_CONTROL.getBytes(StableData.CHARSET_UTF8));
			list.add(0, StableData.HEADER_CONTENT_ENCODING_GZIP.getBytes(StableData.CHARSET_UTF8));
			list.add(0, StableData.HEADER_ACCEPT_RANGES_BYTES.getBytes(StableData.CHARSET_UTF8));
			list.add(0, StableData.HEADER_HOST.getBytes(StableData.CHARSET_UTF8));
			list.add(0, StableData.HEADER_HTTP_200_OK.getBytes(StableData.CHARSET_UTF8));
			if(null != sniper && sniper.length>StableData.INT_ZERO) {
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
		if(null != DetaCacheManager.getCacheOfString(vPCSRequest.getRequestFilePath())){
			builderToString = DetaCacheManager.getCacheOfString(vPCSRequest.getRequestFilePath());
		}else{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(StableData.HEADER_HTTP_200_OK);
			stringBuilder.append(StableData.HEADER_HOST);
			stringBuilder.append(StableData.HEADER_CACHE_CONTROL);
			stringBuilder.append(vPCSResponse.getResponseContentType());
			FileInputStream fileInputStream = new FileInputStream(new File(vPCSRequest.getRequestFilePath())); 
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream
					, vPCSRequest.getRequestFileCode()); 
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader); 
			String line = null; 
			while ((line = bufferedReader.readLine()) != null) { 
				stringBuilder.append(line); 
			}
			bufferedReader.close();
			builderToString = stringBuilder.toString();
			DetaCacheManager.putCacheOfString(vPCSRequest.getRequestFilePath(), builderToString);
		}
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(vPCSResponse.getSocket()
				.getOutputStream(), vPCSRequest.getRequestFileCode()));
		bufferedWriter.write(builderToString);
		bufferedWriter.flush();
		bufferedWriter.close();	
	}

	public static void processBufferBytes(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) 
			throws UnsupportedEncodingException, IOException {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(StableData.HEADER_HTTP_200_OK);
		stringBuilder.append(StableData.HEADER_HOST);
		stringBuilder.append(StableData.HEADER_CACHE_CONTROL);
		stringBuilder.append(StableData.HEADER_CONTENT_ENCODING_GZIP);
		stringBuilder.append(vPCSResponse.getResponseContentType());
		String builderToString = stringBuilder.toString();
		String contentBuilderToString;
		if(null != DetaCacheManager.getCacheOfString(vPCSRequest.getRequestFilePath())){
			contentBuilderToString = DetaCacheManager.getCacheOfString(vPCSRequest.getRequestFilePath());
		}else{
			StringBuilder contentBuilder = new StringBuilder();
			FileInputStream fileInputStream = new FileInputStream(new File(vPCSRequest.getRequestFilePath()));
			int lengthOfFile = StableData.INT_ZERO;
			byte[] byteArray = new byte[StableData.BUFFER_RANGE_MAX];
			while ((lengthOfFile = fileInputStream.read(byteArray)) != StableData.INT_MINES_ONE){
				contentBuilder.append(new String(byteArray, StableData.INT_ZERO, lengthOfFile
						, StableData.CHARSET_UTF_8));
			}
			fileInputStream.close();
			contentBuilderToString = contentBuilder.toString();
			DetaCacheManager.putCacheOfString(vPCSRequest.getRequestFilePath(), contentBuilderToString);
		}	
		DataOutputStream dataOutputStream = new DataOutputStream(vPCSResponse.getSocket().getOutputStream());
		dataOutputStream.write(builderToString.getBytes(StableData.CHARSET_UTF8));
		dataOutputStream.write(GzipUtil.compress(contentBuilderToString.getBytes(StableData.CHARSET_UTF8)));
		dataOutputStream.flush();
		dataOutputStream.close();
	}
}