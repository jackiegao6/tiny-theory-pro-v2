package com.gzc.domain.voucher.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherEntity {

    private Long	id;

    private Long	shopId;

    private String	title;

    private String	subTitle;

    private String	rules;

    private Long	payValue;

    private Long	actualValue;

    private Short	type;

    private Short	status;

    private SeckillVoucherVO seckillVoucherVO;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SeckillVoucherVO{
        private Long voucherId;

        private Integer	stock;

        private Date beginTime;

        private Date endTime;
    }

}
