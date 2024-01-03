package com.hxl.forum.service.ex;

public class FopostNotFoundException extends ServiceException{
    public FopostNotFoundException() {
        super();
    }

    public FopostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FopostNotFoundException(String message) {
        super(message);
    }

    public FopostNotFoundException(Throwable cause) {
        super(cause);
    }

    protected FopostNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
