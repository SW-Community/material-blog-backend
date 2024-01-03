package com.hxl.forum.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostImgPreviewVO {
    public static final int IMG_FILE=0;
    public static final int VID_FILE=1;

    private Integer imgID;
    private String postID;
    private String img;
    private Integer type;
    private Integer topicID;
    private String mpostTitle;
    private String topicName;
    private Integer userID;
    private String userName;
}
