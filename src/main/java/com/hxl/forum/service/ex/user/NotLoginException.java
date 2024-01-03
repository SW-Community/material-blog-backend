package com.hxl.forum.service.ex.user;

import com.hxl.forum.service.ex.ServiceException;

public class NotLoginException extends ServiceException {
    public NotLoginException() {
        super();
    }

    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(Throwable cause) {
        super(cause);
    }

    protected NotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
