package com.hxl.forum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class fileTest {
    @Test
    public void getPath(){
        String currentPath=System.getProperty("user.dir");
        currentPath=currentPath+"\\src\\main\\resources\\upload";
        System.out.println(currentPath);
    }
}
