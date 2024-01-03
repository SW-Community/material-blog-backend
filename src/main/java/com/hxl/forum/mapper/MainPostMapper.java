package com.hxl.forum.mapper;

import com.hxl.forum.entity.MainPost;
import com.hxl.forum.vo.MainPostPreviewVO;

import java.util.Date;
import java.util.List;

public interface MainPostMapper {
    List<MainPost> findAll();
    List<MainPostPreviewVO> findAllPreview();
    List<MainPost> findByUid(Integer uid);
    List<MainPost> findByTid(Integer tid);
    List<MainPost> findByDay(Date day, Integer tid);

    Integer getMainPostCount();
    List<MainPostPreviewVO> getMainPostByPage(Integer start,Integer end);
    Integer getFloorNum(String mid);

    Integer updateFloorNum(String mid,Integer floorNum);
    Integer updateDate(String mid,Date date);
    List<MainPostPreviewVO> findVOByDay(Date day,Integer tid);
    List<MainPost> findByTitle(String title, Integer tid);
    MainPost findByMid(String mid);
    String findLast();
    Integer insert(MainPost mainPost);
    Integer update(MainPost mainPost);
    Integer delete(String mid);
    Integer addLike(String mid);
}
