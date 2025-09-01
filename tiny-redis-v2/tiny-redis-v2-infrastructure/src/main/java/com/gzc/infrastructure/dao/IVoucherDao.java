package com.gzc.infrastructure.dao;

import com.gzc.infrastructure.dao.po.Voucher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IVoucherDao {

    void insert(Voucher voucherReq);

}
