package com.hxl.forum.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoPostPreviewVO {
    private String fpostID;
    private String mpostID;
    private String mpostTitle;
    private Integer userID;
    private Date fpostTime;
    private String fpostIP;
    private String userName;
    private String profile;
    private int ffloor;
    private int refloor;
}
