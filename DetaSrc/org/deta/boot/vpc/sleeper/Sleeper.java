package org.deta.boot.vpc.sleeper;
import java.net.Socket;

import org.deta.boot.vpc.vision.VPCSRequest;
import org.deta.boot.vpc.vision.VPCSResponse;

public class Sleeper extends Thread implements Runnable{
	private VPCSRequest vPCSRequest;
	private VPCSResponse vPCSResponse;

	public Sleeper(){
		vPCSRequest = new VPCSRequest();
		vPCSResponse = new VPCSResponse();
		vPCSResponse.setHashCode(this.hashCode());
	}

	public void run(){
		try{
			//request
			org.deta.boot.vpc.controller.RequestRecordController.requestIpRecoder(vPCSRequest, vPCSResponse);
			org.deta.boot.vpc.controller.RequestRecordController.requestLinkRecoder(vPCSRequest, vPCSResponse);
			org.deta.boot.vpc.controller.RequestFilterController.requestIpFilter(vPCSRequest, vPCSResponse);
			org.deta.boot.vpc.controller.RequestFilterController.requestLinkFilter(vPCSRequest, vPCSResponse);
			org.deta.boot.vpc.controller.RequestFixController.requestIpFix(vPCSRequest, vPCSResponse);
			org.deta.boot.vpc.controller.RequestFixController.requestLinkFix(vPCSRequest, vPCSResponse);
			//process
			org.deta.boot.vpc.vision.ForwardVision.getForwardType(vPCSRequest, vPCSResponse);
			org.deta.boot.vpc.vision.ForwardVision.forwardToRestMap(vPCSRequest, vPCSResponse);
			org.deta.boot.vpc.vision.RestMapVision.getResponse(vPCSRequest, vPCSResponse);
			//response
			org.deta.boot.vpc.vision.RestMapVision.returnResponse(vPCSRequest, vPCSResponse);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void hugPillow(SleeperHall sleeperHall, Socket accept, int hashCode) {
		sleeperHall.addExecSleeper(hashCode, this);
		vPCSResponse.setSocket(accept);
		vPCSResponse.setSleeperHall(sleeperHall);
	}
}