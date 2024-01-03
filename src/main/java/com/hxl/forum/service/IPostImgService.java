package com.hxl.forum.service;

import com.hxl.forum.entity.PostImg;
import com.hxl.forum.vo.PostImgPreviewVO;

import java.util.List;

public interface IPostImgService {
    List<PostImg> getByPid(String pid);
    PostImg getByIid(Integer id);
    void addImg(PostImg postImg);
    void delImg(Integer id);
    void removeImg(Integer id);

    Integer getImageCount();
    Integer getImagePageNum(Integer pageSize);
    List<PostImgPreviewVO> getAImgPage(Integer pageNo,Integer pageSize);
}
