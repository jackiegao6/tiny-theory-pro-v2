package com.gzc.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String id;

    private String phone;

    private String password;

    private String nickName;

    private String icon;

    private Date createTime;

    private Date updateTime;

}
