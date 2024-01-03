package com.hxl.forum.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MainPostPreviewVO {
    private String mpostID;
    private String userName;
    private String mpostTitle;
    private Integer floorNum;
    private String topicName;
    private Date mpostTime;
    private Date lastFoTime;
    private int likeNum;
}
