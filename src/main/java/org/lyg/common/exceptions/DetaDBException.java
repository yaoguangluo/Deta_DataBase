package org.lyg.common.exceptions;

public class DetaDBException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorId;
	private String errorMessage;

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public DetaDBException() {
		super();
	}

	public DetaDBException(String errorId, String errorMessage) {
		super(errorMessage);
		this.errorId = errorId;
		this.errorMessage = errorMessage;
	}
}
