package com.hxl.forum.service.impl;

import com.hxl.forum.entity.MainPost;
import com.hxl.forum.entity.PostImg;
import com.hxl.forum.mapper.FoPostMapper;
import com.hxl.forum.mapper.MainPostMapper;
import com.hxl.forum.mapper.PostImgMapper;
import com.hxl.forum.service.IPostImgService;
import com.hxl.forum.service.ex.FileNotFoundException;
import com.hxl.forum.service.ex.FileUploadException;
import com.hxl.forum.service.ex.PageSizeException;
import com.hxl.forum.service.ex.UpdateException;
import com.hxl.forum.util.RealPathHelper;
import com.hxl.forum.vo.PostImgPreviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
@Service
public class PostImgServiceImpl implements IPostImgService {
    @Resource
    private PostImgMapper postImgMapper;

    @Autowired
    private RealPathHelper realPathHelper;
    @Override
    public List<PostImg> getByPid(String pid) {
        List<PostImg> result=postImgMapper.findByPid(pid);
        if(result == null)
        {
            throw new FileNotFoundException("未找到相关图片");
        }
        return result;
    }

    @Override
    public PostImg getByIid(Integer id) {
        PostImg result=postImgMapper.findByIid(id);
        if(result == null)
        {
            throw new FileNotFoundException("未找到相关图片");
        }
        return result;
    }

    @Override
    public void addImg(PostImg postImg) {
        Integer row=postImgMapper.insertImg(postImg);
        if(row == 0)
        {
            throw new FileUploadException("发生上传错误");
        }
    }

    @Override
    public void delImg(Integer id) {

        Integer row=postImgMapper.delete(id);
        if(row == 0)
        {
            throw new UpdateException("发生删除错误");
        }
    }

    @Override
    @Transactional
    public void removeImg(Integer id)
    {
        PostImg img=postImgMapper.findByIid(id);
        if(img!=null)
        {
            String name= img.getImg();
            String path= realPathHelper.getRealPath();

            //todo:太jb鲨臂了
            path=path.substring(0,"/static/upload/".length());
            File file=new File(path,name);

            file.delete();
        }
        //todo:彻底清理表目


    }


    @Override
    public Integer getImageCount() {
        return postImgMapper.getMDCount();
    }

    @Override
    public Integer getImagePageNum(Integer pageSize) {
        if(pageSize<=0)
        {
            throw new PageSizeException("分页设置错误");
        }

        Integer sum=getImageCount();
        return (sum+pageSize-1)/pageSize;
    }

    @Override
    public List<PostImgPreviewVO> getAImgPage(Integer pageNo, Integer pageSize) {
        Integer pageNum=getImagePageNum(pageSize);
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

        return postImgMapper.getVOByPage(start,end);
    }
}
