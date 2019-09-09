package exceptions;

public class BackupException extends RuntimeException {
	private static final long serialVersionUID = 6164659794492825096L;

	public BackupException() {
		super();
	}

	public BackupException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BackupException(String message, Throwable cause) {
		super(message, cause);
	}

	public BackupException(String message) {
		super(message);
	}

	public BackupException(Throwable cause) {
		super(cause);
	}

}
