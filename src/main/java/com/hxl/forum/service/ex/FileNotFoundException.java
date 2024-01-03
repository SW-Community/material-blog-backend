package com.hxl.forum.service.ex;

public class FileNotFoundException extends ServiceException{
    public FileNotFoundException() {
        super();
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(Throwable cause) {
        super(cause);
    }

    protected FileNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
