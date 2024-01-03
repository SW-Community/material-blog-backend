package com.hxl.forum.service.ex;

public class LikeMyselfException extends ServiceException{
    public LikeMyselfException() {
        super();
    }

    public LikeMyselfException(String message, Throwable cause) {
        super(message, cause);
    }

    public LikeMyselfException(String message) {
        super(message);
    }

    public LikeMyselfException(Throwable cause) {
        super(cause);
    }

    protected LikeMyselfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
