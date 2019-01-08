package org.lyg.common.exceptions;

public enum ErrorCodeEnum {
	ER1001("error."),
	ER1002("invalid key.")
	;

	private String msg;

	private ErrorCodeEnum(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
