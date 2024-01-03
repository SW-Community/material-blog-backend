package com.hxl.forum.mapper;

import com.hxl.forum.entity.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicMapper {
    Integer insert(Topic topic);
    Topic findByName(String topicName);
    List<Topic> getAllTopic();
    Integer updateByID(Topic topic);
    Topic findByID(Integer id);
    Integer deleteByID(Integer topicID);

    List<Topic> getTopicsByPage(Integer start,Integer end);
    Integer getTopicCount();
}