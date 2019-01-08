package org.lyg.common.exceptions;
public class RequestLimitException extends Exception {
	private static final long serialVersionUID = 1364225358754654702L;

	public RequestLimitException() {
		super("HTTP Request Limit...");
	}

	public RequestLimitException(String message) {
		super(message);
	}

}