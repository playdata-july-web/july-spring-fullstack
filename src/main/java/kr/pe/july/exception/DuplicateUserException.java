package kr.pe.july.exception;

@SuppressWarnings("serial")
public class DuplicateUserException extends Exception {
	
	public DuplicateUserException(String msg) {
		super(msg);
	}
}
