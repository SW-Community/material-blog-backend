package com.hxl.forum.service.ex;

public class TopicNotFoundException extends ServiceException{
    public TopicNotFoundException() {
        super();
    }

    public TopicNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicNotFoundException(String message) {
        super(message);
    }

    public TopicNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TopicNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
