package com.hxl.forum.controller.admin;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.Topic;
import com.hxl.forum.service.ITopicService;
import com.hxl.forum.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("admin/topics")
public class TopicManageController extends BaseController {

    @Autowired
    private ITopicService topicService;

    //不能给静态变量赋值！！！！！
    @Value("${list.page_size}")
    private int PAGE_SIZE;

    @RequestMapping("get_topic_page_count")
    public JsonResult<Integer> getTopicPageCount()
    {
        System.out.println("LYH!!!==============="+PAGE_SIZE);
        Integer res = topicService.getTopicPageNum(PAGE_SIZE);
        return new JsonResult<>(OK,res);
    }

    @RequestMapping("get_topic_by_page")
    public JsonResult<List<Topic>> getTopicByPage(Integer pageNo)
    {

        List<Topic> ret = topicService.getATopicPage(pageNo, PAGE_SIZE);
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("get_topic_count")
    public JsonResult<Integer> getTopicCount()
    {
        return new JsonResult<>(OK,topicService.getTopicCount());
    }

    @RequestMapping("delete")
    public JsonResult<Void> deleteTopic(Integer tid)
    {
        topicService.removeTopic(tid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("add")
    public JsonResult<Void> addTopic(Topic topic)
    {
        topicService.addTopic(topic);
        return new JsonResult<>(OK);
    }

    @RequestMapping("update")
    public JsonResult<Void> updateTopic(Topic topic)
    {
        Integer id=topic.getTopicID();
        topicService.updateTopic(id,topic);
        return new JsonResult<>(OK);
    }
}
