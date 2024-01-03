package com.hxl.forum.controller.su;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("admin/su/recycle")
public class RecycleBinController extends BaseController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private IUsersService usersService;
    @Autowired
    private ITopicService topicService;
    @Autowired
    private IMainPostService mainPostService;
    @Autowired
    private IFoPostService foPostService;
    @Autowired
    private IPostImgService postImgService;

}
