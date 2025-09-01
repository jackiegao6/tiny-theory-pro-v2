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
public class Voucher {

    /**
     * 主键
     */
    private Long	id;

    /**
     * 商铺id
     */
    private Long	shopId;

    /**
     * 代金券标题
     */
    private String	title;

    /**
     * 副标题
     */
    private String	subTitle;

    /**
     * 使用规则
     */
    private String	rules;

    /**
     * 支付金额，单位是分。例如200代表2元
     */
    private Long	payValue;

    /**
     * 抵扣金额，单位是分。例如200代表2元
     */
    private Long	actualValue;

    /**
     * 0,普通券；1,秒杀券
     */
    private Short	type;

    /**
     * 1,上架; 2,下架; 3,过期
     */
    private Short	status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date	updateTime;

}
