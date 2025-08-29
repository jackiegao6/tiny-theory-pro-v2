package com.gzc.infrastructure.adapter.repository;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.gzc.domain.shop.adapter.repository.IShopRepository;
import com.gzc.domain.shop.model.entity.ShopInfoEntity;
import com.gzc.infrastructure.dao.IShopDao;
import com.gzc.infrastructure.dao.po.Shop;
import com.gzc.infrastructure.redis.IRedisService;
import com.gzc.types.common.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
@RequiredArgsConstructor
public class ShopRepository implements IShopRepository {

    private final IRedisService redisService;
    private final IShopDao shopDao;

    @Override
    public ShopInfoEntity queryShopById(Long id) {

        String shopInfoEntityJson = redisService.getValue(Constants.SHOP_KEY + id);
        if (StrUtil.isNotBlank(shopInfoEntityJson)){
            return JSON.parseObject(shopInfoEntityJson, ShopInfoEntity.class);
        }

        Shop shopRes = shopDao.queryShopById(id);
        if (null == shopRes) return null;

        ShopInfoEntity shopInfoEntity = ShopInfoEntity.builder()
                .name(shopRes.getName())
                .typeId(shopRes.getTypeId())
                .images(shopRes.getImages())
                .area(shopRes.getArea())
                .address(shopRes.getAddress())
                .x(shopRes.getX())
                .y(shopRes.getY())
                .avgPrice(shopRes.getAvgPrice())
                .sold(shopRes.getSold())
                .comments(shopRes.getComments())
                .score(shopRes.getScore())
                .openHours(shopRes.getOpenHours())
                .build();
        redisService.setValue(Constants.SHOP_KEY + id, shopInfoEntity);
        return shopInfoEntity;
    }
}
