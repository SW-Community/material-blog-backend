package com.hxl.forum.controller.user;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.MainPost;
import com.hxl.forum.service.IMainPostService;
import com.hxl.forum.util.JsonResult;
import com.hxl.forum.util.UserTokenHelper;
import com.hxl.forum.vo.MainPostPreviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("mposts")
public class MainPostController extends BaseController {

    @Autowired
    private UserTokenHelper userTokenHelper;

    @Autowired
    private IMainPostService mainPostService;

    @RequestMapping("get_all")
    public JsonResult<List<MainPost>> getAll() {
        List<MainPost> data = mainPostService.getAll();
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("get_all_preview")
    public JsonResult<List<MainPostPreviewVO>> getAllPreview() {
        return new JsonResult<>(OK, mainPostService.findAllPreview());
    }

    @RequestMapping("get_by_uid")
    public JsonResult<List<MainPost>> getByUid(Integer uid) {
        List<MainPost> data = mainPostService.getByUid(uid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("get_by_tid")
    public JsonResult<List<MainPost>> getByTid(int tid) {
        List<MainPost> data = mainPostService.getByTid(tid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("get_by_day")
    public JsonResult<List<MainPost>> getByDay(String day, int tid) {
        List<MainPost> data = mainPostService.getByDay(day, tid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("get_by_title")
    public JsonResult<List<MainPost>> getByTitle(String title, int tid) {
        List<MainPost> data = mainPostService.getByTitle(title, tid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("get_by_mid")
    public JsonResult<MainPost> getByMid(String mid) {
        MainPost data = mainPostService.getByMid(mid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("check_my_post")
    public JsonResult<Boolean> checkMyPost(String mid, HttpSession session) {
        Integer uid = userTokenHelper.getUidFromSession(session);
        Boolean data = mainPostService.checkMyPost(mid, uid);
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("add_like")
    public JsonResult<Void> addLike(String mid, HttpSession session) {
        Integer uid = userTokenHelper.getUidFromSession(session);
        mainPostService.addLike(uid, mid);
        return new JsonResult<>(OK);
    }

    @PostMapping("new_mpost")
    public JsonResult<Void> newPost(MainPost post, HttpSession session) {
        Integer uid = userTokenHelper.getUidFromSession(session);
        mainPostService.addMpost(uid, post);
        return new JsonResult<>(OK);
    }

    @RequestMapping("edit_mpost")
    public JsonResult<Void> editPost(MainPost post, String mid, HttpSession session) {
        Integer uid = userTokenHelper.getUidFromSession(session);
        mainPostService.editMpost(uid, mid, post);
        return new JsonResult<>(OK);
    }

    @RequestMapping("del_mpost")
    public JsonResult<Void> delPost(String mid, HttpSession session) {
        Integer uid = userTokenHelper.getUidFromSession(session);
        mainPostService.delMpost(uid, mid);
        return new JsonResult<>(OK);
    }
}
