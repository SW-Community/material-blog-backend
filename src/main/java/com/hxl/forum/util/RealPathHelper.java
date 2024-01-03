package com.hxl.forum.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RealPathHelper {
    @Value("${static.real_path}")
    private String realPath;

    @Value("${static.use_user_dir}")
    private boolean useUserDir;

    @Value("${static.use_project_dir}")
    private boolean useProjectDir;

    public String getRealPath()
    {
        if(useUserDir)
        {
            String parent=System.getProperty("user.home");
            return parent+realPath;
        }
        else if (useProjectDir)
        {
            String parent=System.getProperty("user.dir");
            return parent+realPath;
        }
        else
        {
            return realPath;
        }
    }
}
