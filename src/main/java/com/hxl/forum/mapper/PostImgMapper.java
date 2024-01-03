package com.hxl.forum.mapper;

import com.hxl.forum.entity.PostImg;
import com.hxl.forum.vo.PostImgPreviewVO;

import java.util.List;

public interface PostImgMapper {
    List<PostImg> findByPid(String pid);
    PostImg findByIid(Integer id);

    @Deprecated
    Integer insert(PostImg postImg);
    Integer delete(Integer id);
    Integer insertImg(PostImg postImg);


    Integer getMDCount();

    List<PostImgPreviewVO> getVOByPage(Integer start,Integer end);
}
