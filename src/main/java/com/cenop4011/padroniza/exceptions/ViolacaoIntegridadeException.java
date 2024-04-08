package com.cenop4011.padroniza.exceptions;

public class ViolacaoIntegridadeException  extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViolacaoIntegridadeException(String message) {
        super(message);
    }

    public ViolacaoIntegridadeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    
    
    
}