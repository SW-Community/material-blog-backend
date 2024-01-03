package com.hxl.forum.service;

import com.hxl.forum.entity.MainPost;
import com.hxl.forum.vo.MainPostPreviewVO;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface IMainPostService {

    List<MainPost> getAll();
    List<MainPostPreviewVO> findAllPreview();
    List<MainPost> getByUid(Integer uid);
    List<MainPost> getByTid(Integer tid);
    List<MainPost> getByDay(String day, Integer tid);
    List<MainPost> getByTitle(String title, Integer tid);
    MainPost getByMid(String mid);
    Boolean checkMyPost(String mid, Integer uid);
    void addMpost(Integer uid, MainPost mainPost);
    void addLike(Integer uid,String mid);
    void editMpost(Integer uid, String id,MainPost post);
    void delMpost(Integer uid, String id);

    Integer getMainPostCount();
    Integer getMainPostPageNum(Integer pageSize);
    List<MainPostPreviewVO> getAMPPage(Integer pageNo,Integer pageSzie);
}
