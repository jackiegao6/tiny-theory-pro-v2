package com.gzc.domain.shop.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopInfoEntity {

    private Long id;

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


}
