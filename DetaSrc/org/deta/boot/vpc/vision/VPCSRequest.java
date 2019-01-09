package org.deta.boot.vpc.vision;

import java.util.Map;

public class VPCSRequest {
	private String requestIp;
	private String requestName;
	private String requestLink;
	private boolean requestIsRest;
	private String requestFilePath;
	private String requestFileCode;
	private String requestForwardType;

	private String requestIpFix;
	private String requestNameFix;
	private String requestLinkFix;
	private Map<String, String> requestValue;
	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	public String getRequestLink() {
		return requestLink;
	}

	public void setRequestLink(String requestLink) {
		this.requestLink = requestLink;
	}

	public String getRequestIpFix() {
		return requestIpFix;
	}

	public void setRequestIpFix(String requestIpFix) {
		this.requestIpFix = requestIpFix;
	}

	public String getRequestNameFix() {
		return requestNameFix;
	}

	public void setRequestNameFix(String requestNameFix) {
		this.requestNameFix = requestNameFix;
	}

	public String getRequestLinkFix() {
		return requestLinkFix;
	}

	public void setRequestLinkFix(String requestLinkFix) {
		this.requestLinkFix = requestLinkFix;
	}

	public Map<String, String> getRequestValue() {
		return requestValue;
	}

	public void setRequestValue(Map<String, String> requestValue) {
		this.requestValue = requestValue;
	}

	public boolean getRequestIsRest() {
		return requestIsRest;
	}

	public void setRequestIsRest(boolean requestIsRest) {
		this.requestIsRest = requestIsRest;
	}

	public String getRequestForwardType() {
		return requestForwardType;
	}

	public void setRequestForwardType(String requestForwardType) {
		this.requestForwardType = requestForwardType;
	}

	public String getRequestFilePath() {
		return requestFilePath;
	}

	public void setRequestFilePath(String requestFilePath) {
		this.requestFilePath = requestFilePath;
	}

	public String getRequestFileCode() {
		return requestFileCode;
	}

	public void setRequestFileCode(String requestFileCode) {
		this.requestFileCode = requestFileCode;
	}
}