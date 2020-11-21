package com.eagle.interview.drawimg;

/**
 * 业务异常
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2483599972506697940L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
}