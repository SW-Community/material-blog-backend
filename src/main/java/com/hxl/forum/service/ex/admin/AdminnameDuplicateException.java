package com.hxl.forum.service.ex.admin;

import com.hxl.forum.service.ex.ServiceException;

public class AdminnameDuplicateException extends ServiceException {
    public AdminnameDuplicateException() {
        super();
    }

    public AdminnameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminnameDuplicateException(String message) {
        super(message);
    }

    public AdminnameDuplicateException(Throwable cause) {
        super(cause);
    }

    protected AdminnameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
