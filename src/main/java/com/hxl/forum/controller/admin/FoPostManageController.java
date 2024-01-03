package com.hxl.forum.controller.admin;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.FoPost;
import com.hxl.forum.service.IFoPostService;
import com.hxl.forum.util.AdminTokenHelper;
import com.hxl.forum.util.JsonResult;
import com.hxl.forum.vo.FoPostPreviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("admin/fpost")
public class FoPostManageController extends BaseController {

    @Autowired
    private IFoPostService foPostService;

    @Autowired
    private AdminTokenHelper adminTokenHelper;

    @Value("${list.page_size}")
    private int PAGE_SIZE;

    @RequestMapping("get_fp_page_count")
    public JsonResult<Integer> getFPPageCount()
    {
        Integer ret= foPostService.getFoPostPageNum(PAGE_SIZE);
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("get_fp_by_page")
    public JsonResult<List<FoPostPreviewVO>> getFPByPage(Integer pageNo)
    {
        System.out.println("刘一涵");
        List<FoPostPreviewVO> ret=foPostService.getAFPPage(pageNo,PAGE_SIZE);
        return new JsonResult<>(OK,ret);
    }


    @RequestMapping("get_one")
    public JsonResult<FoPost> getByFid(String fid)
    {
        FoPost ret=foPostService.getByFid(fid);
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("delete")
    public JsonResult<Void> deleteByFid(String id, HttpSession session)
    {
        Integer aid= adminTokenHelper.getAidFromSession(session);
        foPostService.delPost(aid,id);
        return new JsonResult<>(OK);
    }
}
