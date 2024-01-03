package com.hxl.forum.service.impl;

import com.hxl.forum.entity.MainPost;
import com.hxl.forum.entity.PostImg;
import com.hxl.forum.entity.Users;
import com.hxl.forum.mapper.FoPostMapper;
import com.hxl.forum.mapper.MainPostMapper;
import com.hxl.forum.mapper.PostImgMapper;
import com.hxl.forum.mapper.UsersMapper;
import com.hxl.forum.service.IMainPostService;
import com.hxl.forum.service.ex.*;
import com.hxl.forum.vo.MainPostPreviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MainPostServiceImpl implements IMainPostService {
    @Resource
    private MainPostMapper mainPostMapper;
    @Resource
    private UsersMapper usersMapper;

    //todo:增加图片功能
    @Resource
    private PostImgMapper postImgMapper;

    @Resource
    private FoPostMapper foPostMapper;

    //我看行
    @Override
    public List<MainPost> getAll() {
        List<MainPost> result=mainPostMapper.findAll();
        if(result == null)
            throw new MainpostNotFoundException("暂无相关帖子");
        Users user;
        for(MainPost m:result){
            user=usersMapper.findByUid(m.getUserID());
            if(user == null)
                continue;
            m.setUserName(user.getUserName());
            m.setProfile(user.getProfile());
        }
        return result;
    }

    @Override
    public List<MainPostPreviewVO> findAllPreview() {
        return mainPostMapper.findAllPreview();
    }

    @Override
    public List<MainPost> getByUid(Integer uid) {
        List<MainPost> result=mainPostMapper.findByUid(uid);
        if(result == null)
            throw new MainpostNotFoundException("暂无相关帖子");
        Users user=usersMapper.findByUid(uid);
        for(MainPost m:result){
            m.setUserName(user.getUserName());
            m.setProfile(user.getProfile());
        }
        return result;
    }

    @Override
    public List<MainPost> getByTid(Integer tid) {
        List<MainPost> result=mainPostMapper.findByTid(tid);
        if(result == null)
            throw new MainpostNotFoundException("暂无相关帖子");
        Users user;
        for(MainPost m:result){
            user=usersMapper.findByUid(m.getUserID());
            if(user == null)
                continue;
            m.setUserName(user.getUserName());
            m.setProfile(user.getProfile());
        }
        return result;
    }

    @Override
    public List<MainPost> getByDay(String day, Integer tid) {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        List<MainPost> result;
        Date date;
        try{
            date = dateFormat1.parse(day);

        }catch(ParseException e){
            throw new DateErrorException("查询日期有误");
        }
        result = mainPostMapper.findByDay(date,tid);
        if(result == null)
            throw new MainpostNotFoundException("暂无相关帖子");
        Users user;
        for(MainPost m:result){
            user=usersMapper.findByUid(m.getUserID());
            if(user == null)
                continue;
            m.setUserName(user.getUserName());
            m.setProfile(user.getProfile());
        }
        return result;
    }

    @Override
    public List<MainPost> getByTitle(String title, Integer tid) {
        List<MainPost> result=mainPostMapper.findByTitle(title, tid);
        if(result == null)
            throw new MainpostNotFoundException("暂无相关帖子");
        Users user;
        for(MainPost m:result){
            user=usersMapper.findByUid(m.getUserID());
            if(user == null)
                continue;
            m.setUserName(user.getUserName());
            m.setProfile(user.getProfile());
        }
        return result;
    }

    @Override
    public MainPost getByMid(String mid) {
        MainPost m=mainPostMapper.findByMid(mid);
        if(m == null)
            throw new MainpostNotFoundException("无相关帖子");
        return m;
    }

    @Override
    public Boolean checkMyPost(String mid, Integer uid) {
        MainPost result=mainPostMapper.findByMid(mid);
        if(result==null)
            throw new MainpostNotFoundException("相关帖子不存在");
        if(Objects.equals(result.getUserID(), uid))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    @Override
    public void addMpost(Integer uid,MainPost mainPost) {
        mainPost.setUserID(uid);
        mainPost.setFloorNum(0);
        mainPost.setLastFoTime(new Date());
        mainPost.setLikeNum(0);
        Integer row=mainPostMapper.insert(mainPost);
        if(row == 0)
        {
            throw new InsertException("帖子内容插入错误");
        }
    }

    @Override
    public void addLike(Integer uid, String mid) {
        MainPost result=mainPostMapper.findByMid(mid);
        if(result == null)
        {
            throw new MainpostNotFoundException("无相关帖子");
        }
        if(Objects.equals(result.getUserID(), uid))
        {
            throw new LikeMyselfException("不可以给自己点赞哦");
        }
        Integer row=mainPostMapper.addLike(mid);
        if(row == 0)
        {
            throw new UpdateException("发生更新错误");
        }
    }

    @Override
    public void editMpost(Integer uid, String id, MainPost post) {
        MainPost result=mainPostMapper.findByMid(id);
        if(result == null)
            throw new MainpostNotFoundException("无相关帖子");
        post.setMpostID(id);
        post.setUserID(uid);
        Integer row=mainPostMapper.update(post);
        if(row == 0)
            throw new InsertException("插入错误");
    }

    @Override
    @Transactional
    public void delMpost(Integer uid, String id) {
        MainPost result=mainPostMapper.findByMid(id);
        if(result == null)
            throw new MainpostNotFoundException("无相关帖子");
        Integer row=mainPostMapper.delete(id);
        if(row == 0)
        {
            throw new UpdateException("删除错误");
        }
        //删除所有的回复
        List<String> ids=foPostMapper.getAllIDsByMPost(id);
        if(ids!=null)
        {
            for (String fid : ids) {
                foPostMapper.delete(fid);
            }
        }

        //todo:实现日志系统
    }

    @Override
    public Integer getMainPostCount() {
        return mainPostMapper.getMainPostCount();
    }

    @Override
    public Integer getMainPostPageNum(Integer pageSize) {
        if(pageSize<=0)
        {
            throw new PageSizeException("分页设置错误");
        }
        Integer sum=getMainPostCount();
        return (sum+pageSize-1)/pageSize;
    }

    @Override
    public List<MainPostPreviewVO> getAMPPage(Integer pageNo, Integer pageSize) {
        Integer pageNum=getMainPostPageNum(pageSize);
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
        return mainPostMapper.getMainPostByPage(start,end);
    }
}
