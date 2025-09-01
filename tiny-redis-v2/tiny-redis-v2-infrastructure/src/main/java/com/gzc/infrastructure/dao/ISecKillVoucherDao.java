package com.gzc.infrastructure.dao;

import com.gzc.infrastructure.dao.po.SeckillVoucher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISecKillVoucherDao {


    void insert(SeckillVoucher seckillVoucher);

}
