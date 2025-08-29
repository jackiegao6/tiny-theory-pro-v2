package com.gzc.domain.shop.service;


import com.gzc.domain.shop.adapter.repository.IShopRepository;
import com.gzc.domain.shop.model.entity.ShopInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService implements IShopService{

    private final IShopRepository shopRepository;

    @Override
    public ShopInfoEntity queryShopById(Long id) {

        return shopRepository.queryShopById(id);
    }
}
