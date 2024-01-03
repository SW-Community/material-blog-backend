package com.hxl.forum.service.ex;

public class TopicnameDulicateException extends ServiceException{

    public TopicnameDulicateException() {
        super();
    }

    public TopicnameDulicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicnameDulicateException(String message) {
        super(message);
    }

    public TopicnameDulicateException(Throwable cause) {
        super(cause);
    }

    protected TopicnameDulicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
