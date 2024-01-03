package com.hxl.forum.service.ex;

public class DateErrorException extends ServiceException{
    public DateErrorException() {
        super();
    }

    public DateErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateErrorException(String message) {
        super(message);
    }

    public DateErrorException(Throwable cause) {
        super(cause);
    }

    protected DateErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
