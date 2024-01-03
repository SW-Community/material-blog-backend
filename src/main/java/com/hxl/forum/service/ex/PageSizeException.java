package com.hxl.forum.service.ex;

public class PageSizeException extends ServiceException{
    public PageSizeException() {
        super();
    }

    public PageSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageSizeException(String message) {
        super(message);
    }

    public PageSizeException(Throwable cause) {
        super(cause);
    }

    protected PageSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
