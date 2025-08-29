package com.gzc.trigger.http;


import com.gzc.api.response.Response;
import com.gzc.domain.shop.model.entity.ShopInfoEntity;
import com.gzc.domain.shop.service.IShopService;
import com.gzc.types.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1/hmdp/shop")
@RequiredArgsConstructor
public class ShopController {

    private final IShopService shopService;

    @RequestMapping(value = "/shop/{id}", method = RequestMethod.GET)
    public Response<ShopInfoEntity> queryShopById(@PathVariable("id") Long id){

        ShopInfoEntity shopInfoEntity = shopService.queryShopById(id);
        if (null == shopInfoEntity){
            return Response.<ShopInfoEntity>builder()
                    .code(ResponseCode.SHOP_NOT_EXIST.getCode())
                    .info(ResponseCode.SHOP_NOT_EXIST.getInfo())
                    .data(null)
                    .build();
        }
        return Response.<ShopInfoEntity>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .data(shopInfoEntity)
                .build();
    }


}
