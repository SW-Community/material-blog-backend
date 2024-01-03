package com.hxl.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MainPost implements Serializable {
    private String mpostID;
    private Integer topicID;
    private Integer userID;
    private String userName;
    private String profile;
    private String mpostTitle;
    private Integer floorNum;
    private Date mpostTime;
    private String mpostIP;
    private String mpostContent;
    private Date lastFoTime;
    private int likeNum;
    public MainPost(Integer tid,Integer uid,String title,String IP,String content){
        this.topicID=tid;
        this.userID=uid;
        this.mpostTitle=title;
        this.mpostContent=content;
        this.mpostIP=IP;
    }
}
