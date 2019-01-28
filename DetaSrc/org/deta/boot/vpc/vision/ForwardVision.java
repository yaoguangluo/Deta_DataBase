package org.deta.boot.vpc.vision;
import java.io.IOException;
import java.net.Socket;
import org.deta.boot.rest.VPC;
import org.lyg.stable.StableData;

public class ForwardVision {
	public static void main(String[] args){
	}

	public static void getForwardType(Socket socket) {
	}

	public static void forwardToRestMap(Socket socket) {
	}

	public static void getForwardType(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws IOException {
		if(vPCSRequest.getRequestIsRest()){
			String filePath = VPC.getFilePath(vPCSRequest.getRequestLink());
			if(filePath.contains(StableData.FILE_TTF)||filePath.contains(StableData.FILE_EOT)
					||filePath.contains(StableData.FILE_SVG)||filePath.contains(StableData.FILE_WOFF)
					||filePath.contains(StableData.FILE_WOFF2)||filePath.contains(StableData.FILE_OTF)){
				String code = VPC.getCode(filePath);
				vPCSRequest.setRequestFilePath(filePath);
				vPCSRequest.setRequestFileCode(code);
				vPCSRequest.setRequestForwardType(StableData.STREAM_BUFFER);
				vPCSResponse.setResponseContentType(StableData.HEADER_CONTENT_TYPE_FONT_WOFF);
				return;
			}	
			vPCSRequest.setRequestForwardType(StableData.STREAM_REST);
		}else{
			String filePath = VPC.getFilePath(vPCSRequest.getRequestLink());
			String code = VPC.getCode(filePath);
			vPCSRequest.setRequestFilePath(filePath);
			vPCSRequest.setRequestFileCode(code);
			if(filePath.contains(StableData.FILE_PNG)){ 
				vPCSRequest.setRequestForwardType(StableData.STREAM_BYTES);
				vPCSResponse.setResponseContentType(StableData.HEADER_CONTENT_TYPE_PNG);
			}
			if(filePath.contains(StableData.FILE_JPEG)){ 
				vPCSRequest.setRequestForwardType(StableData.STREAM_BYTES);
				vPCSResponse.setResponseContentType(StableData.HEADER_CONTENT_TYPE_JPEG);
			}
			if(filePath.contains(StableData.FILE_JPG)){ 
				vPCSRequest.setRequestForwardType(StableData.STREAM_BYTES);
				vPCSResponse.setResponseContentType(StableData.HEADER_CONTENT_TYPE_JPG);
			}
			if(filePath.contains(StableData.FILE_GIF)){ 
				vPCSRequest.setRequestForwardType(StableData.STREAM_BYTES);
				vPCSResponse.setResponseContentType(StableData.HEADER_CONTENT_TYPE_GIF);
			}
			if(filePath.contains(StableData.FILE_JS) && code.equalsIgnoreCase(StableData.CHARSET_UTF_8)){	
				vPCSRequest.setRequestForwardType(StableData.STREAM_BYTES_BUFFER);
				vPCSResponse.setResponseContentType(StableData.HEADER_CONTENT_TYPE_JS);
			}
			if(filePath.contains(StableData.FILE_CSS)){
				vPCSRequest.setRequestForwardType(StableData.STREAM_BUFFER);
				vPCSResponse.setResponseContentType(StableData.HEADER_CONTENT_TYPE_CSS);
			}
			if(filePath.contains(StableData.FILE_HTML)){
				vPCSRequest.setRequestForwardType(StableData.STREAM_BUFFER);
				vPCSResponse.setResponseContentType(StableData.HEADER_CONTENT_TYPE_HTML);
			}
			if(filePath.contains(StableData.FILE_WAV)){
				vPCSRequest.setRequestForwardType(StableData.STREAM_BUFFER);
				vPCSResponse.setResponseContentType(StableData.HEADER_CONTENT_TYPE_WAV);
			}
		}	
	}

	public static void forwardToRestMap(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws Exception {
		if(null == vPCSRequest || null == vPCSRequest.getRequestForwardType()){
			vPCSResponse.return404();
			return;
		}
		if(vPCSRequest.getRequestForwardType().equalsIgnoreCase(StableData.STREAM_REST)){
			RestMapVision.processRest(vPCSRequest, vPCSResponse);
		}
		if(vPCSRequest.getRequestForwardType().equalsIgnoreCase(StableData.STREAM_BYTES)){
			RestMapVision.processBytes(vPCSRequest, vPCSResponse);
		}
		if(vPCSRequest.getRequestForwardType().equalsIgnoreCase(StableData.STREAM_BUFFER)){
			RestMapVision.processBuffer(vPCSRequest, vPCSResponse);
		}
		if(vPCSRequest.getRequestForwardType().equalsIgnoreCase(StableData.STREAM_BYTES_BUFFER)){
			RestMapVision.processBufferBytes(vPCSRequest, vPCSResponse);
		}
	}
}