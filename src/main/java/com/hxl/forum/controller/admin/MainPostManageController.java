package com.hxl.forum.controller.admin;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.MainPost;
import com.hxl.forum.service.IMainPostService;
import com.hxl.forum.util.AdminTokenHelper;
import com.hxl.forum.util.JsonResult;
import com.hxl.forum.vo.MainPostPreviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("admin/mpost")
public class MainPostManageController extends BaseController {

    @Autowired
    private IMainPostService mainPostService;

    @Autowired
    private AdminTokenHelper adminTokenHelper;

    @Value("${list.page_size}")
    private int PAGE_SIZE;

    @RequestMapping("get_mp_page_count")
    public JsonResult<Integer> getMPPageCount()
    {
        Integer ret=mainPostService.getMainPostPageNum(PAGE_SIZE);
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("get_mp_by_page")
    public JsonResult<List<MainPostPreviewVO>> getMPByPage(Integer pageNo)
    {
        System.out.println("MYLYH!!!==============="+PAGE_SIZE);
        List<MainPostPreviewVO> ret=mainPostService.getAMPPage(pageNo,PAGE_SIZE);
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("get_mp_count")
    public JsonResult<Integer> getMPCount()
    {
        Integer ret= mainPostService.getMainPostCount();
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("update")
    public JsonResult<Void> updateMainPost(MainPost mainPost)
    {
        Integer uid=mainPost.getUserID();
        String id=mainPost.getMpostID();
        mainPostService.editMpost(uid,id,mainPost);
        return new JsonResult<>(OK);
    }

    @RequestMapping("delete")
    public JsonResult<Void> deleteMainPost(String id, HttpSession session)
    {
        Integer uid=adminTokenHelper.getAidFromSession(session);
        //todo:实现日志系统
        mainPostService.delMpost(uid,id);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_one")
    public JsonResult<MainPost> getbyId(String mid){
        MainPost ret=mainPostService.getByMid(mid);
        return new JsonResult<>(OK,ret);
    }
}
