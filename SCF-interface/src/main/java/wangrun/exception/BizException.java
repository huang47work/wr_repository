package wangrun.exception;

/**
 * {@link RuntimeException}
 * 
 * @author guojie
 * @since Dec 9,2016
 * @version 1.0.1
 *
 */
public class BizException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object error;

	public BizException() {
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BizException(Object error) {
		this.error = error;
	}

	public Object getError() {
		return error;
	}
}
