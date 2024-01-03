package com.hxl.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {
    private Integer userID;
    private String userName;
    private String password;
    private String salt;
    private String email;
    private Integer gender;
    private String profile;
    private int isDelete=0;
    public Users(String name,String password,String salt,String email){
        this.userName=name;
        this.password=password;
        this.salt=salt;
        this.email=email;
    }
    public Users(String name,String password,String salt){
        this.userName=name;
        this.password=password;
        this.salt=salt;
    }
}
