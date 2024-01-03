package com.hxl.forum.controller;

import com.hxl.forum.service.ex.*;
import com.hxl.forum.service.ex.admin.AdminnameDuplicateException;
import com.hxl.forum.service.ex.admin.SuReadonlyEception;
import com.hxl.forum.service.ex.user.NotLoginException;
import com.hxl.forum.service.ex.user.UserNotFoundException;
import com.hxl.forum.service.ex.user.UserNotMatchException;
import com.hxl.forum.service.ex.user.UsernameDuplicateException;
import com.hxl.forum.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    public static final int OK = 200;
    //todo:改写幻数

    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);

        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
        } else if(e instanceof NotLoginException){
            result.setState(4003);
        }

        else if(e instanceof AdminnameDuplicateException) {
            result.setState(4100);
        } else if(e instanceof SuReadonlyEception) {
            result.setState(4101);
        }

        else if (e instanceof InsertException) {
            result.setState(5000);
        } else if (e instanceof UpdateException) {
            result.setState(5001);
        }

        else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }

        else if(e instanceof TopicnameDulicateException){
            result.setState(7000);
        }else if(e instanceof TopicNotFoundException){
            result.setState(7001);
        }

        else if(e instanceof MainpostNotFoundException){
            result.setState(8000);
        }else if(e instanceof UserNotMatchException){
            result.setState(8001);
        }
        return result;
    }
}
