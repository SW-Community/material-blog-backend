package com.hxl.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    private Integer aid;
    private String adminName;
    private String password;
    private String salt;
}
