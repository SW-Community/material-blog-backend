package com.hxl.forum.controller.user;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.Topic;
import com.hxl.forum.service.ITopicService;
import com.hxl.forum.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("topics")
public class TopicController extends BaseController {
    @Autowired
    private ITopicService topicService;
    @RequestMapping("getall")
    public JsonResult<List<Topic>> getall(){
        List<Topic> data=topicService.getAll();
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("new_topic")
    public JsonResult<Void> newTopic(Topic topic){
        topicService.addTopic(topic);
        return new JsonResult<>(OK);
    }
    @RequestMapping("edit_topic")
    public JsonResult<Void> editTopic(Integer tid, Topic topic){
        topicService.updateTopic(tid,topic);
        return new JsonResult<>(OK);
    }
    @RequestMapping("del_topic")
    public JsonResult<Void> delTopic(Integer id){
        topicService.removeTopic(id);
        return new JsonResult<>(OK);
    }
}
