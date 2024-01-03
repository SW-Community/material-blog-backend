package com.hxl.forum.controller.admin;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.PostImg;
import com.hxl.forum.service.IPostImgService;
import com.hxl.forum.util.AdminTokenHelper;
import com.hxl.forum.util.JsonResult;
import com.hxl.forum.vo.PostImgPreviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("admin/postimg")
public class MediaManageController extends BaseController {

    @Autowired
    private AdminTokenHelper adminTokenHelper;

    @Autowired
    private IPostImgService postImgService;

    @Value("${list.page_size}")
    private int PAGE_SIZE;


    @RequestMapping("get_media_page_count")
    public JsonResult<Integer> getMediaPageCount()
    {
        Integer ret=postImgService.getImagePageNum(PAGE_SIZE);
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("get_media_by_page")
    public JsonResult<List<PostImgPreviewVO>> getMediaByPage(Integer pageNo)
    {
        List<PostImgPreviewVO> ret=postImgService.getAImgPage(pageNo,PAGE_SIZE);
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("get_by_pid")
    public JsonResult<List<PostImg>> getByPid(String pid)
    {
        List<PostImg> ret = postImgService.getByPid(pid);
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("delete")
    public JsonResult<Void> delete(Integer iid)
    {
        postImgService.delImg(iid);
        return new JsonResult<>(OK);
    }
}
