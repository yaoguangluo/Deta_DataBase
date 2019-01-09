package org.deta.boot.vpc.vision;
import java.net.Socket;
import org.deta.boot.vpc.sleeper.SleeperHall;
public class VPCSResponse{
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public SleeperHall getSleeperHall() {
		return sleeperHall;
	}

	public void setSleeperHall(SleeperHall sleeperHall) {
		this.sleeperHall = sleeperHall;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getResponseContentType() {
		return ResponseContentType;
	}

	public void setResponseContentType(String responseContentType) {
		ResponseContentType = responseContentType;
	}
	
	private Socket socket;
	private SleeperHall sleeperHall;
	private String hashCode;
	private int errorCode;
	private String ResponseContentType;
}