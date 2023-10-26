package com.cenop4011.padroniza.exceptions;

public class PersonalBadRequest extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public PersonalBadRequest(String message) {
        super(message);
    }

    public PersonalBadRequest(String message, Throwable cause) {
        super(message, cause);
    }

}
