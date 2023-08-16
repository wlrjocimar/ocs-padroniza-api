package com.cenop4011.padroniza.exceptions;

public class ConstraintException  extends RuntimeException {
	
	
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConstraintException(String message) {
        super(message);
    }

    public ConstraintException(String message, Throwable cause) {
        super(message, cause);
    }

}
