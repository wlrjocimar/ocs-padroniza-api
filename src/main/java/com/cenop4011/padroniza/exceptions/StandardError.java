package com.cenop4011.padroniza.exceptions;

public class StandardError {
    
    private Long timestamp;
    private Integer status;
    private String messageError;

    public StandardError() {
        super();
    }

    public StandardError(Long timestamp, Integer status, String messageError) {
        this.timestamp = timestamp;
        this.status = status;
        this.messageError = messageError;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
    
    
    
    
    
}