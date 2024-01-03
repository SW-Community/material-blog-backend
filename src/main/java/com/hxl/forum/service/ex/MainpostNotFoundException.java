package com.hxl.forum.service.ex;

public class MainpostNotFoundException extends ServiceException{
    public MainpostNotFoundException() {
        super();
    }

    public MainpostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MainpostNotFoundException(String message) {
        super(message);
    }

    public MainpostNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MainpostNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
