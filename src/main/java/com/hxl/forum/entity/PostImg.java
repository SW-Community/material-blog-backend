package com.hxl.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostImg implements Serializable {

    public static final int IMG_FILE=0;
    public static final int VID_FILE=1;

    private Integer imgID;
    private String postID;
    private String img;
    private Integer type;

    public PostImg(String postID,String img){
        this.img=img;
        this.postID=postID;
    }
}
