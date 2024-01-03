package com.hxl.forum.controller.su;


import com.hxl.forum.entity.Admin;
import com.hxl.forum.service.IAdminService;
import com.hxl.forum.util.AdminTokenHelper;
import com.hxl.forum.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hxl.forum.controller.BaseController;

@CrossOrigin
@RestController
@RequestMapping("admin/su/admins")
public class AdminManageController extends BaseController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private AdminTokenHelper adminTokenHelper;

    @Value("${list.page_size}")
    private int PAGE_SIZE;

    @RequestMapping("reg")
    public JsonResult<Void> reg(Admin admin)
    {

        adminService.reg(admin);
        return new JsonResult<>(OK);
    }


}
