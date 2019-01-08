package org.lyg.vpc.view;
 
public class UsrToken {
	public String getuLevel() {
		return uLevel;
	}
	public void setuLevel(String uLevel) {
		this.uLevel = uLevel;
	}
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public String getuKey() {
		return uKey;
	}
	public void setuKey(String uKey) {
		this.uKey = uKey;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getuToken() {
		return uToken;
	}
	public void setuToken(String uToken) {
		this.uToken = uToken;
	}
	public long getuTime() {
		return uTime;
	}
	public void setuTime(long uTime) {
		this.uTime = uTime;
	}
	private Integer uId;
	private String uKey;
	private String uPassword;
	private String uToken;
	private String uLevel;
	private long uTime;
}