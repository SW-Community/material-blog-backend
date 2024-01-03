package com.hxl.forum.service.impl;

import com.hxl.forum.entity.FoPost;
import com.hxl.forum.entity.MainPost;
import com.hxl.forum.entity.PostImg;
import com.hxl.forum.entity.Users;
import com.hxl.forum.mapper.FoPostMapper;
import com.hxl.forum.mapper.MainPostMapper;
import com.hxl.forum.mapper.PostImgMapper;
import com.hxl.forum.mapper.UsersMapper;
import com.hxl.forum.service.IFoPostService;
import com.hxl.forum.service.ex.*;
import com.hxl.forum.vo.FoPostPreviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class FoPostServiceImpl implements IFoPostService {
    @Resource
    private FoPostMapper foPostMapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private PostImgMapper postImgMapper;

    @Resource
    private MainPostMapper mainPostMapper;

    @Override
    public List<FoPost> getAll() {
        List<FoPost> result=foPostMapper.getAll();
        if(result == null)
            throw  new FopostNotFoundException("暂无回帖");
        Users user;
        for(FoPost f:result){
            user=usersMapper.findByUid(f.getUserID());
            if(user == null)
                continue;
            f.setUserName(user.getUserName());
            f.setProfile(user.getProfile());
        }
        return result;
    }

    @Override
    public List<FoPost> getByMid(String mid) {
        List<FoPost> result=foPostMapper.getByMid(mid);
        if(result == null)
            throw new FopostNotFoundException("暂无相关回帖");
        Users user;
        for(FoPost f:result){
            user=usersMapper.findByUid(f.getUserID());
            if(user == null)
                continue;
            f.setUserName(user.getUserName());
            f.setProfile(user.getProfile());
        }
        return result;
    }

    @Override
    public void addLike(Integer uid, String fid) {
        FoPost result=foPostMapper.getByFid(fid);
        if(result == null)
            throw new FopostNotFoundException("无相关帖子");
        if(Objects.equals(result.getUserID(), uid))
            throw new LikeMyselfException("不能给自己点赞哦");
        foPostMapper.addLike(fid);
    }

    @Override
    @Transactional
    public void addPost(Integer uid, FoPost post) {
        post.setUserID(uid);
        String mid=post.getMpostID();
        Integer floorNum= mainPostMapper.getFloorNum(mid);
        if(floorNum==null)
        {
            throw new FopostNotFoundException("无相关帖子");
        }
        post.setFfloor(floorNum+1);
        System.out.println("fffff"+(floorNum+1));
        Date date=new Date();
        post.setFpostTime(date);
        Integer row=foPostMapper.insert(post);
        if(row == 0) {
            throw new InsertException("发生插入错误");
        }
        row=mainPostMapper.updateFloorNum(mid,floorNum+1);
        if(row==0)
        {
            throw new InsertException("发生修改错误");
        }
        row=mainPostMapper.updateDate(mid,date);
        if(row==0)
        {
            throw new InsertException("发生修改错误");
        }
    }

    @Override
    @Transactional
    public void delPost(Integer uid, String fid) {
        FoPost result=foPostMapper.getByFid(fid);
        if(result == null)
            throw new FopostNotFoundException("无相关帖子");
        Integer currentFloor=result.getFfloor();
        String mainPostID=result.getMpostID();
        Integer row=foPostMapper.delete(fid);
        if(row == 0) {
            throw new UpdateException("跟帖删除错误");
        }
        //删除所有的回帖
        List<String> ids=foPostMapper.getAllIDsByReply(mainPostID,currentFloor);
        if(ids!=null){
            for (String id : ids) {
                foPostMapper.delete(id);
            }
        }
    }

    @Override
    public Boolean checkMyPost(Integer uid, String fid) {
        FoPost result=foPostMapper.getByFid(fid);
        if(result == null)
            throw new FopostNotFoundException("无相关帖子");
        return Objects.equals(result.getUserID(), uid);
    }


    @Override
    public FoPost getByFid(String fid) {
        return foPostMapper.getByFid(fid);
    }

    @Override
    public Integer getFoPostCount() {
        return foPostMapper.getFPCount();
    }

    @Override
    public Integer getFoPostPageNum(Integer pageSize) {
        if(pageSize<=0)
        {
            throw new PageSizeException("分页设置错误");
        }
        Integer sum=getFoPostCount();
        return (sum+pageSize-1)/pageSize;
    }

    @Override
    public List<FoPostPreviewVO> getAFPPage(Integer pageNo, Integer pageSize) {
        Integer pageNum=getFoPostPageNum(pageSize);
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
        return foPostMapper.getByPage(start,end);
    }
}
