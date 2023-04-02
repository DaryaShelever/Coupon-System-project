package app.core.exception;

import java.sql.SQLException;

public class CouponsSystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponsSystemException(SQLException e) {
	}

	public CouponsSystemException(String message) {
		super(message);
	}

	public CouponsSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponsSystemException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
