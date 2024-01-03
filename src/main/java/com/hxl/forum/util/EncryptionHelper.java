package com.hxl.forum.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Component
public class EncryptionHelper {
    /*
     * 加密规则：
     * 1、无视原始密码的强度
     * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
     * 3、循环加密3次
     */
    public String emitSalt()
    {
       return UUID.randomUUID().toString().toUpperCase();
    }

    public String getMd5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password +
                    salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
