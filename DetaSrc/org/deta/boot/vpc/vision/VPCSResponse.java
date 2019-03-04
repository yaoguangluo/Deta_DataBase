package org.deta.boot.vpc.vision;
import java.io.IOException;
import java.io.PrintWriter;
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

	public Integer getHashCode() {
		return hashCode;
	}

	public void setHashCode(Integer hashCode) {
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
	private Integer hashCode;
	private int errorCode;
	private String ResponseContentType;
	public void return404() throws IOException {
		if(socket.isClosed()) {
			this.sleeperHall.removeThreadById(this.hashCode);
			return;
		}
		PrintWriter pw = new PrintWriter(this.socket.getOutputStream(), true);
		pw.println("HTTP/1.1 404 OK\n\n"); 
		pw.flush();
		pw.close();
		socket.close();
		this.sleeperHall.removeThreadById(this.hashCode);
	}

	public void returnErrorCode(Integer errorCode) throws IOException {
		if(socket.isClosed()) {
			this.sleeperHall.removeThreadById(this.hashCode);
			return;
		}
		PrintWriter pw = new PrintWriter(this.socket.getOutputStream(), true);
		pw.println("HTTP/1.1 " + errorCode + " OK\n\n"); 
		pw.flush();
		pw.close();
		socket.close();
		this.sleeperHall.removeThreadById(this.hashCode);
	}
}