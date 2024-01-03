package com.hxl.forum.service.ex.user;

import com.hxl.forum.service.ex.ServiceException;

public class UserNotMatchException extends ServiceException {
    public UserNotMatchException() {
        super();
    }

    public UserNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotMatchException(String message) {
        super(message);
    }

    public UserNotMatchException(Throwable cause) {
        super(cause);
    }

    protected UserNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
