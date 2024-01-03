package com.hxl.forum.service.ex.user;

import com.hxl.forum.service.ex.ServiceException;

public class UsernameDuplicateException extends ServiceException {
    public UsernameDuplicateException() {
        super();
    }

    public UsernameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicateException(String message) {
        super(message);
    }

    public UsernameDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
