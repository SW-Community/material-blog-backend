package com.hxl.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic implements Serializable {
    private Integer topicID;
    private String topicName;
    private String topicIntro;
    public Topic(String name,String intro){
        this.topicIntro=intro;
        this.topicName=name;
    }
}
