package com.gzc.domain.shop.adapter.repository;

import com.gzc.domain.shop.model.entity.ShopInfoEntity;

public interface IShopRepository {

    ShopInfoEntity queryShopById(Long id);


}
