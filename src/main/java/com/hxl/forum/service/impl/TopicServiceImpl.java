package com.hxl.forum.service.impl;

import com.hxl.forum.entity.Topic;
import com.hxl.forum.mapper.TopicMapper;
import com.hxl.forum.service.IMainPostService;
import com.hxl.forum.service.ITopicService;
import com.hxl.forum.service.ex.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
public class TopicServiceImpl implements ITopicService {
    @Resource
    private TopicMapper topicMapper;

    //todo:互调
    @Resource
    private IMainPostService mainPostService;

    @Override
    public List<Topic> getAll() {
        List<Topic> result=topicMapper.getAllTopic();
        if(result == null)
            throw new TopicNotFoundException("无相关版面");
        return result;
    }

    @Override
    public Integer getTopicCount() {
        return topicMapper.getTopicCount();
    }

    @Override
    public Integer getTopicPageNum(Integer pageSize) {
        if(pageSize<=0)
        {
            throw new PageSizeException("分页设置错误");
        }
        Integer sum= getTopicCount();
        System.out.println("MSY========================="+sum);
        int pageNumber=(sum+pageSize-1)/pageSize;
        return pageNumber;
    }

    @Override
    public List<Topic> getATopicPage(Integer pageNo, Integer pageSize) {
        //防止越界
        Integer pageNum=getTopicPageNum(pageSize);
        if(pageNo<1)
        {
            pageNo=1;
        }
        if(pageNo>pageNum)
        {
            pageNo=pageNum;
        }
        int start=(pageNo-1)*pageSize;
        int end=pageSize;
        return topicMapper.getTopicsByPage(start,end);
    }

    @Override
    public void addTopic(Topic topic) {
        String name=topic.getTopicName();
        Topic result=topicMapper.findByName(name);
        if(result != null)
            throw new TopicnameDulicateException("已存在该版面");
        Integer row=topicMapper.insert(topic);
        if(row == 0)
            throw new InsertException("发生插入错误");
    }

    @Override
    public void updateTopic(Integer id, Topic topic) {
        Topic result=topicMapper.findByID(id);
        if(result==null)
            throw new TopicNotFoundException("无相关版面");
        topic.setTopicID(id);
        Integer row = topicMapper.updateByID(topic);
        if(row == 0)
            throw new InsertException("发生插入错误");
    }

    //todo:主题与帖子级联删除
    @Override
    @Transactional
    public void removeTopic(Integer topicID) {
        Topic result=topicMapper.findByID(topicID);
        if(result==null)
            throw new TopicNotFoundException("无相关版面");
        Integer id=result.getTopicID();
        Integer row=topicMapper.deleteByID(topicID);
        if(row==0)
            throw new UpdateException("发生删除错误");

        //todo:根据id删除主贴

    }
}
