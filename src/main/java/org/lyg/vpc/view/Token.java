package org.lyg.vpc.view;

public class Token {
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuKey() {
		return uKey;
	}
	public void setuKey(String uKey) {
		this.uKey = uKey;
	}
	public String getmPassword() {
		return mPassword;
	}
	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	public long getuTime() {
		return uTime;
	}
	public void setuTime(long uTime) {
		this.uTime = uTime;
	}
	private String uEmail;
	private String uKey;
	private String mPassword;
	private long uTime;
}