package com.hxl.forum.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("foPost")
public class FoPost implements Serializable {

    private String fpostID;
    private String mpostID;
    private Integer userID;
    private Date fpostTime;
    private String fpostIP;
    private String fpostContent;
    private String userName;
    private String profile;
    private int ffloor;
    private int refloor;
    public FoPost(String mpostID,Integer userID,String ip,String content,int refloor){
        this.fpostContent=content;
        this.mpostID=mpostID;
        this.userID=userID;
        this.fpostIP = ip;
        this.refloor=refloor;
    }
}
