package org.deta.boot.vpc.vision;

import java.io.IOException;
import java.net.Socket;

import org.deta.boot.rest.VPC;

public class ForwardVision {
	public static void main(String[] args){

	}

	public static void getForwardType(Socket socket) {
		// TODO Auto-generated method stub
		
	}

	public static void forwardToRestMap(Socket socket) {
		// TODO Auto-generated method stub
		
	}

	public static void getForwardType(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws IOException {
		if(vPCSRequest.getRequestIsRest()){
			vPCSRequest.setRequestForwardType("rest");
		}else{
			String filePath = VPC.getFilePath(vPCSRequest.getRequestLink());
			String code = VPC.getCode(filePath);
			vPCSRequest.setRequestFilePath(filePath);
			vPCSRequest.setRequestFileCode(code);
			if(filePath.contains(".png")){ 
				vPCSRequest.setRequestForwardType("bytes");
				vPCSResponse.setResponseContentType("Content-Type: image/png \n\n");
			}
			if(filePath.contains(".jpeg")){ 
				vPCSRequest.setRequestForwardType("bytes");
				vPCSResponse.setResponseContentType("Content-Type: image/jpeg \n\n");
			}
			if(filePath.contains(".gif")){ 
				vPCSRequest.setRequestForwardType("bytes");
				vPCSResponse.setResponseContentType("Content-Type: image/gif \n\n");
			}
			if(filePath.contains(".js") && code.equalsIgnoreCase("UTF-8")){	
				vPCSRequest.setRequestForwardType("bytesBuffer");
			}
		
			if(filePath.contains(".css")){
				vPCSRequest.setRequestForwardType("buffer");
				vPCSResponse.setResponseContentType("Content-Type: text/css \n\n");
			}
			if(filePath.contains(".html")){
				vPCSRequest.setRequestForwardType("buffer");
				vPCSResponse.setResponseContentType("Content-Type: text/html \n\n");
			}
		}
		
	}

	public static void forwardToRestMap(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws Exception {
		if(vPCSRequest.getRequestForwardType() == null){
			vPCSResponse.return404();
		}
		if(vPCSRequest.getRequestForwardType().equalsIgnoreCase("rest")){
			RestMapVision.processRest(vPCSRequest, vPCSResponse);
		}
		if(vPCSRequest.getRequestForwardType().equalsIgnoreCase("bytes")){
			RestMapVision.processBytes(vPCSRequest, vPCSResponse);
		}
		if(vPCSRequest.getRequestForwardType().equalsIgnoreCase("buffer")){
			RestMapVision.processBuffer(vPCSRequest, vPCSResponse);
		}
		if(vPCSRequest.getRequestForwardType().equalsIgnoreCase("bytesBuffer")){
			RestMapVision.processBufferBytes(vPCSRequest, vPCSResponse);
		}
	}
}