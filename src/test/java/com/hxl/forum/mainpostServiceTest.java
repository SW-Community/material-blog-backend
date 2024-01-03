package com.hxl.forum;

import com.hxl.forum.entity.FoPost;
import com.hxl.forum.entity.MainPost;
import com.hxl.forum.service.IFoPostService;
import com.hxl.forum.service.IMainPostService;
import com.hxl.forum.service.ITopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class mainpostServiceTest {
    @Autowired
    private IMainPostService mainPostService;
    @Autowired
    private IFoPostService foPostService;
    @Autowired
    private ITopicService topicService;
    @Test
    public void getByTid(){
        Integer tid=1001;
        List<MainPost> result=mainPostService.getByTid(tid);
        for(MainPost m:result)
            System.out.println(m);
    }
    @Test
    public void getByUid(){
        Integer uid=1;
        List<MainPost> result=mainPostService.getByUid(uid);
        for(MainPost m:result)
            System.out.println(m);
    }
    @Test
    public void getByDay(){
        String day="2022-06-11";
        List<MainPost> result=mainPostService.getByDay(day,1001);
        for(MainPost m:result)
            System.out.println(m);
    }
    @Test
    public void addFo(){
        FoPost f=new FoPost("m0",1,null,"回复楼层测试",1);
        foPostService.addPost(1,f);
    }
    @Test
    public void delTopic(){
        Integer tid=3002;
        topicService.removeTopic(3002);
    }
}
