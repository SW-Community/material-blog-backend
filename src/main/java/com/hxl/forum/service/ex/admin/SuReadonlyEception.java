package com.hxl.forum.service.ex.admin;

import com.hxl.forum.service.ex.ServiceException;

public class SuReadonlyEception extends ServiceException {
    public SuReadonlyEception() {
        super();
    }

    public SuReadonlyEception(String message, Throwable cause) {
        super(message, cause);
    }

    public SuReadonlyEception(String message) {
        super(message);
    }

    public SuReadonlyEception(Throwable cause) {
        super(cause);
    }

    protected SuReadonlyEception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
