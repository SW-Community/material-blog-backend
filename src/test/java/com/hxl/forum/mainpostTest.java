package com.hxl.forum;

import com.hxl.forum.entity.MainPost;
import com.hxl.forum.mapper.MainPostMapper;
import com.hxl.forum.vo.MainPostPreviewVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class mainpostTest {
    @Autowired
    MainPostMapper mainPostMapper;
    
    @Test
    public void ddd()
    {
        List<MainPostPreviewVO> res = mainPostMapper.findAllPreview();
        for (MainPostPreviewVO re : res) {
            System.out.println(re);
        }
    }

    @Test
    public void eee()
    {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = dateFormat1.parse("2023-12-08");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<MainPostPreviewVO> res=mainPostMapper.findVOByDay(date,1);
        System.out.println("黄欣灵====================");
        for (MainPostPreviewVO vo : res) {

            System.out.println(vo);
        }
    }

    @Test
    public void fff()
    {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = dateFormat1.parse("2023-12-08");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(date);
        List<MainPost> res;
        res=mainPostMapper.findByDay(date,1);
        System.out.println("黄欣灵");
        if(res!=null&& !res.isEmpty()){
            for (MainPost post : res) {
                System.out.println(post);
            }
        }

    }
    
    @Test
    public void findByUid(){
        Integer uid=1;
        List<MainPost> result=mainPostMapper.findByUid(uid);
        for(MainPost m:result)
            System.out.println(m);
    }
    @Test
    public void findByTid(){
        Integer tid=1001;
        List<MainPost> result=mainPostMapper.findByTid(tid);
        for(MainPost m:result)
            System.out.println(m);
    }
    @Test
    public void findByDay() throws ParseException {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date day = dateFormat1.parse("2022-06-12");
        List<MainPost> result=mainPostMapper.findByDay(day,1001);
        for(MainPost m:result)
            System.out.println(m);
    }
    @Test
    public void findByTitle(){
        String title="八旬老汉";
        List<MainPost> result=mainPostMapper.findByTitle(title,1001);
        for(MainPost m:result)
            System.out.println(m);
    }
    @Test
    public void findLast(){
        String mid=mainPostMapper.findLast();
        System.out.println(mid);
    }
}
