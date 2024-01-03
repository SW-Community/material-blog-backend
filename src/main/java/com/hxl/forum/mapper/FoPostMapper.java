package com.hxl.forum.mapper;

import com.hxl.forum.entity.FoPost;
import com.hxl.forum.vo.FoPostPreviewVO;

import java.util.List;

public interface FoPostMapper {
    List<FoPost> getAll();
    List<FoPost> getByMid(String mid);
    List<FoPostPreviewVO> getVoByMid(String mid);
    FoPost getByFid(String fid);
    List<FoPost> getAllByReply(String mid,Integer refloor);
    List<String> getAllIDsByReply(String mid,Integer refloor);
    List<String> getAllIDsByMPost(String mid);

    String findLast();
    Integer insert(FoPost foPost);
    Integer delete(String fid);
    Integer addLike(String fid);

    Integer getFPCount();
    List<FoPostPreviewVO> getByPage(Integer start,Integer end);
}
