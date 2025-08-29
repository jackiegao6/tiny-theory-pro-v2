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
public class Shop {

    private String name;

    private long typeId;

    private String images;

    private String area;

    private String address;

    private Double x;

    private Double y;

    private Integer avgPrice;

    private Integer sold;

    private Integer comments;

    private Integer score;

    private String openHours;

    private Date createTime;

    private Date updateTime;


}
