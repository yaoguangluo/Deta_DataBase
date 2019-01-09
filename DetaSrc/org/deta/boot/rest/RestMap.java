package org.deta.boot.rest;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.lyg.cache.DetaCacheManager;
public class RestMap {
	@SuppressWarnings("resource")
	public static void process(String[] type, Socket socket, Boolean isRest) throws IOException {
		String output = "";
		try {
			if(isRest){
				String[] column = type[1].split("&");
				Map<String, String> data = new ConcurrentHashMap<>();
				for(String cell:column){
					String[] cells = cell.split("=");
					data.put(cells[0], URLDecoder.decode(cells[1], "UTF-8"));
				}
				output = VPC.forward(type[0], data);
				PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
						.getOutputStream(),"UTF-8")),true);
				pw.println("HTTP/1.1 200 OK\n\n"); 
				output=output.charAt(0)=='"'?output.substring(1,output.length()):output;
				output=output.charAt(output.length()-1)=='"'?output.substring(0
						, output.length()-1):output;
				pw.println(output.replace("\\\"","\""));
				pw.flush();
				pw.close();
			}
			String filePath = VPC.getFilePath(type[0]);
			String code = VPC.getCode(filePath);	
			if(filePath.contains(".png") || filePath.contains(".jpeg") || filePath.contains(".gif")){
				List<byte[]> list;
				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
				if(DetaCacheManager.getCacheOfBytesList(filePath) != null){
					list = DetaCacheManager.getCacheOfBytesList(filePath);
				}else{
					FileInputStream fileInputStream = new FileInputStream(new File(filePath));
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
					if(filePath.contains(".png")){
						list.add(0, ("Content-Type: image/png \n\n".getBytes()));
					}
					if(filePath.contains(".jpeg")){
						list.add(0, ("Content-Type: image/jpeg \n\n".getBytes()));
					}
					if(filePath.contains(".gif")){
						list.add(0, ("Content-Type: image/gif \n\n".getBytes()));
					}
					list.add(0, ("Content-Length: " + tlen + " \n").getBytes());
					list.add(0, "Accept-Ranges: bytes \n".getBytes());
					list.add(0, "http/1.1 200 ok \n".getBytes());
					DetaCacheManager.putCacheOfBytesList(filePath, list);
				}	 
				Iterator<byte[]> iterator = list.iterator();
				while(iterator.hasNext()){
					dataOutputStream.write(iterator.next());
				}		
				dataOutputStream.flush();
				dataOutputStream.close();
			}else{
				String builderToString;
				if(DetaCacheManager.getCacheOfString(filePath) != null){
					builderToString = DetaCacheManager.getCacheOfString(filePath);
				}else{
					builderToString = VPC.forwardLink(filePath, null, code).toString();
					DetaCacheManager.putCacheOfString(filePath, builderToString);
				}
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), code));
				bufferedWriter.write(builderToString);
				bufferedWriter.flush();
				bufferedWriter.close();	
			}
		} catch (Exception e) {
			PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
			pw.format("UTF-8", pw);
			pw.println("HTTP/1.1 500 OK\n\n"); 
			pw.println(output);
			pw.flush();
			pw.close();
		}	
	}
}
