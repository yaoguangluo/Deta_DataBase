package org.deta.boot.vpc.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.deta.boot.vpc.vision.VPCSRequest;
import org.deta.boot.vpc.vision.VPCSResponse;
import org.lyg.stable.StableData;
public class RequestRecordController {

	public static void requestIpRecoder(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) {
		vPCSRequest.setRequestIp(vPCSResponse.getSocket().getInetAddress().getHostAddress());
		vPCSRequest.setRequestName(vPCSResponse.getSocket().getInetAddress().getHostName());
	}

	public static void requestLinkRecoder(VPCSRequest vPCSRequest, VPCSResponse vPCSResponse) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(vPCSResponse.getSocket().getInputStream()
				, StableData.CHARSET_GBK));
		String mess = br.readLine();
		if(null == mess){
			vPCSResponse.returnErrorCode(StableData.HTTP_400);
			return;
		}
		if(mess.equalsIgnoreCase(StableData.STRING_EMPTY)){
			vPCSResponse.returnErrorCode(StableData.HTTP_400);
			return;
		}
		String[] type = mess.split(StableData.STRING_SPACE);
		if(type.length < StableData.INT_TWO){
			vPCSResponse.returnErrorCode(StableData.HTTP_500);
			return;
		}
		String[] content = type[StableData.INT_ONE].split(StableData.STRING_SLASH_QUESTION);
		if(content.length == StableData.INT_TWO){
			vPCSRequest.setRequestIsRest(true);
			if(content[StableData.INT_ONE] == null){
				vPCSResponse.returnErrorCode(StableData.HTTP_500);
				return;
			}
		}
		if(content[StableData.INT_ZERO].contains(StableData.STRING_QUATE)){
			//			vPCSRequest.setRequestIsRest(false);
		}
		if(vPCSRequest.getRequestIsRest()){
			String[] column = content[StableData.INT_ONE].split(StableData.STRING_JUNCTION);
			Map<String, String> data = new ConcurrentHashMap<>();
			for(String cell:column){
				String[] cells = cell.split(StableData.MATH_EQUAL);
				data.put(cells[StableData.INT_ZERO], URLDecoder.decode(cells[StableData.INT_ONE], StableData.CHARSET_UTF_8));
			}
			vPCSRequest.setRequestValue(data);	
		}
		vPCSRequest.setRequestLink(content[StableData.INT_ZERO]);	
	}
}