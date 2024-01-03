package com.hxl.forum.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<E> implements Serializable {
    private Integer state;
    private String message;
    private E data;
    public JsonResult() {
        super();
    }
    public JsonResult(Integer state) {
        super();
        this.state = state;
    }
    /** 出现异常时调用 */
    public JsonResult(Throwable e) {
        super();
// 获取异常对象中的异常信息
        this.message = e.getMessage();
    }
    public JsonResult(Integer state, E data) {
        super();
        this.state = state;
        this.data = data;
    }

}
