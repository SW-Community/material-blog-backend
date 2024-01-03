package com.hxl.forum.controller.user;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.FoPost;
import com.hxl.forum.service.IFoPostService;
import com.hxl.forum.util.JsonResult;
import com.hxl.forum.util.UserTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("fposts")
public class FoPostController extends BaseController {
    @Autowired
    private IFoPostService foPostService;

    @Autowired
    private UserTokenHelper userTokenHelper;

    //todo:权力分离
    @Deprecated
    @RequestMapping("get_all")
    public JsonResult<List<FoPost>> getAll(){
        List<FoPost> data=foPostService.getAll();
        return new JsonResult<>(OK,data);
    }


    @RequestMapping("get_by_mid")
    public JsonResult<List<FoPost>> getByMid(String mid){
        List<FoPost> data=foPostService.getByMid(mid);
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("new_fpost")
    public JsonResult<Void> newFpost(FoPost post, HttpSession session){
        System.out.println("mylyh============");
        System.out.println(post);
        Integer uid=userTokenHelper.getUidFromSession(session);
        foPostService.addPost(uid,post);
        return new JsonResult<>(OK);
    }

    @Deprecated
    @RequestMapping("check_my_post")
    public JsonResult<Boolean> checkMyPost(String fid,HttpSession session){
        Integer uid=userTokenHelper.getUidFromSession(session);
        Boolean data=foPostService.checkMyPost(uid,fid);
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("add_like")
    public JsonResult<Void> addLike(String fid,HttpSession session){
        Integer uid=userTokenHelper.getUidFromSession(session);
        foPostService.addLike(uid,fid);
        return new JsonResult<>(OK);
    }
    @RequestMapping("del_fpost")
    public JsonResult<Void> delFpost(String fid,HttpSession session){
        Integer uid=userTokenHelper.getUidFromSession(session);
        foPostService.delPost(uid,fid);
        return new JsonResult<>(OK);
    }
}
