package com.gzc.infrastructure.dao;

import com.gzc.infrastructure.dao.po.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IShopDao {

    Shop queryShopById(Long id);


}
