package com.hxl.forum.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
