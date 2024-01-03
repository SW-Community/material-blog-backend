package com.hxl.forum.controller.user;

import com.hxl.forum.controller.BaseController;
import com.hxl.forum.entity.PostImg;
import com.hxl.forum.service.IPostImgService;
import com.hxl.forum.service.ex.*;
import com.hxl.forum.util.JsonResult;
import com.hxl.forum.util.RealPathHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("postimg")
public class PostImgController extends BaseController {

    @Autowired
    private IPostImgService postImgService;

    @Autowired
    private RealPathHelper realPathHelper;

    public static final int IMG_FILE_MAX_SIZE = 10 * 1024 * 1024;
    public static final int VID_FILE_MAX_SIZE=1500*1024*1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> IMG_FILE_TYPES = new ArrayList<>();
    public static final List<String> VID_FILE_TYPES=new ArrayList<>();

    static {
        IMG_FILE_TYPES.add("image/jpg");
        IMG_FILE_TYPES.add("image/png");
        IMG_FILE_TYPES.add("image/bmp");
        IMG_FILE_TYPES.add("image/gif");


        VID_FILE_TYPES.add("video/mpeg4");
        VID_FILE_TYPES.add("video/avi");
        VID_FILE_TYPES.add("video/x-mpeg");
        VID_FILE_TYPES.add("video/mpg");
        VID_FILE_TYPES.add("video/x-ms-wmv");
        VID_FILE_TYPES.add("video/mpeg");
    }

    @RequestMapping("get_by_pid")
    public JsonResult<List<PostImg>> getByArticle(String postID)
    {
        List<PostImg> ret = postImgService.getByPid(postID);
        return new JsonResult<>(OK,ret);
    }

    @RequestMapping("add_img")
    public JsonResult<Void> addImg(@RequestParam("mfiles") MultipartFile[] mfiles, String postID)
    {
        System.out.println(postID);
        System.out.println("沐思遥");
        if(mfiles!=null&&mfiles.length!=0)
        {
            for (MultipartFile file : mfiles) {
                if (file.isEmpty()) {
                    // 是：抛出异常
                    System.out.println("文件空");
                    throw new FileEmptyException("上传的文件不允许为空");
                }
                PostImg postImg=new PostImg();
                postImg.setPostID(postID);
                // 判断上传的文件大小是否超出限制值

                String contentType = file.getContentType();
                if (IMG_FILE_TYPES.contains(contentType))
                {
                    System.out.println("图片");
                    if (file.getSize() > IMG_FILE_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
                        // 是：抛出异常
                        System.out.println("oom");
                        throw new FileSizeException("不允许上传超过" + ( IMG_FILE_MAX_SIZE/ 1024) + "KB的图片文件");
                    }
                    postImg.setType(PostImg.IMG_FILE);
                }

                else if(VID_FILE_TYPES.contains(contentType))
                {
                    System.out.println("视频");
                    if (file.getSize() > VID_FILE_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
                        // 是：抛出异常
                        System.out.println("oom");
                        throw new FileSizeException("不允许上传超过" + (VID_FILE_MAX_SIZE / 1024) + "KB的视频文件");
                    }
                    postImg.setType(PostImg.VID_FILE);
                }
                else {
                    System.out.println("类型错误");
                    throw new FileTypeException("不支持此文件");
                }

                String parent= realPathHelper.getRealPath();
                System.out.println("真实路径为："+parent);
                File dir = new File(parent);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String suffix = "";
                String originalFilename = file.getOriginalFilename();
                int beginIndex = 0;
                if (originalFilename != null) {
                    beginIndex = originalFilename.lastIndexOf(".");
                }
                if (beginIndex > 0) {
                    suffix = originalFilename.substring(beginIndex);
                }
                String filename = UUID.randomUUID() + suffix;

                File dest = new File(dir, filename);
                try {
                    file.transferTo(dest);
                } catch (IllegalStateException e) {
                    System.out.println("!1");
                    throw new FileStateException("文件状态异常，可能文件已被移动或删除");
                } catch (IOException e) {
                    System.out.println("!2");
                    throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
                }

                String path = "/static/upload/" + filename;
                postImg.setImg(path);
                System.out.println("inserting.....");
                postImgService.addImg(postImg);
            }
        }
        else {
            System.out.println("empty");
        }
        return new JsonResult<>(OK);
    }

    @RequestMapping("delete_img")
    public JsonResult<Void> deleteImg(Integer imgId)
    {
        postImgService.delImg(imgId);
        return new JsonResult<>(OK);
    }
}
