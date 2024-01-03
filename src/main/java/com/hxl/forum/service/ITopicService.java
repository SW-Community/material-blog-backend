package com.hxl.forum.service;

import com.hxl.forum.entity.Topic;

import java.util.List;

public interface ITopicService {
    List<Topic> getAll();

    Integer getTopicCount();
    Integer getTopicPageNum(Integer pageSize);
    List<Topic> getATopicPage(Integer pageNo,Integer pageSize);

    void addTopic(Topic topic);
    void updateTopic(Integer id, Topic topic);
    void removeTopic(Integer id);
}
