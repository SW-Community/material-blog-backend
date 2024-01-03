package com.hxl.forum.service;

import com.hxl.forum.entity.FoPost;
import com.hxl.forum.vo.FoPostPreviewVO;

import java.util.List;

public interface IFoPostService {
    List<FoPost> getAll();
    List<FoPost> getByMid(String mid);
    void addLike(Integer uid,String fid);
    void addPost(Integer uid,FoPost post);
    void delPost(Integer uid,String fid);
    Boolean checkMyPost(Integer uid,String fid);

    FoPost getByFid(String fid);

    Integer getFoPostCount();
    Integer getFoPostPageNum(Integer pageSize);

    List<FoPostPreviewVO> getAFPPage(Integer pageNo,Integer pageSize);
}
