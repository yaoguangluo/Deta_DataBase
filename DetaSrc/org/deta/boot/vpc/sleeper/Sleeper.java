package org.deta.boot.vpc.sleeper;
import java.io.IOException;
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
			org.deta.boot.vpc.controller.RequestRecordController.requestIpRecoder(vPCSRequest, vPCSResponse);
			if(vPCSResponse.getSocket().isClosed()) {
				return;
			}
			org.deta.boot.vpc.controller.RequestRecordController.requestLinkRecoder(vPCSRequest, vPCSResponse);
			if(vPCSResponse.getSocket().isClosed()) {
				return;
			}
			org.deta.boot.vpc.controller.RequestFilterController.requestIpFilter(vPCSRequest, vPCSResponse);
			if(vPCSResponse.getSocket().isClosed()) {
				return;
			}
			org.deta.boot.vpc.controller.RequestFilterController.requestLinkFilter(vPCSRequest, vPCSResponse);
			if(vPCSResponse.getSocket().isClosed()) {
				return;
			}
			org.deta.boot.vpc.controller.RequestFixController.requestIpFix(vPCSRequest, vPCSResponse);
			if(vPCSResponse.getSocket().isClosed()) {
				return;
			}
			org.deta.boot.vpc.controller.RequestFixController.requestLinkFix(vPCSRequest, vPCSResponse);
			if(vPCSResponse.getSocket().isClosed()) {
				return;
			}
			org.deta.boot.vpc.vision.ForwardVision.getForwardType(vPCSRequest, vPCSResponse);
			if(vPCSResponse.getSocket().isClosed()) {
				return;
			}
			org.deta.boot.vpc.vision.ForwardVision.forwardToRestMap(vPCSRequest, vPCSResponse);
			if(vPCSResponse.getSocket().isClosed()) {
				return;
			}
			org.deta.boot.vpc.vision.RestMapVision.getResponse(vPCSRequest, vPCSResponse);
			if(vPCSResponse.getSocket().isClosed()) {
				return;
			}
			org.deta.boot.vpc.vision.RestMapVision.returnResponse(vPCSRequest, vPCSResponse);
			if(vPCSResponse.getSocket().isClosed()) {
				return;
			}		
		}catch(Exception e){
			try {
				vPCSResponse.returnErrorCode(500);
				e.printStackTrace();
			} catch (IOException e1) {
				System.gc();
				e1.printStackTrace();
			}
		}
	}

	public void hugPillow(SleeperHall sleeperHall, Socket accept, int hashCode) {
		sleeperHall.addExecSleeper(hashCode, this);
		vPCSResponse.setSocket(accept);
		vPCSResponse.setSleeperHall(sleeperHall);
	}
}