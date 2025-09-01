package com.gzc.trigger.http;


import com.gzc.api.response.Response;
import com.gzc.domain.voucher.model.entity.VoucherEntity;
import com.gzc.domain.voucher.service.IVoucherService;
import com.gzc.types.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/hmdp/voucher")
public class VoucherController {

    private final IVoucherService voucherService;

    /**
{
    "shopId": 1,
    "title": "test_title",
    "subTitle": "test_sub_title",
    "rules": "no rule",
    "payValue": 900,
    "actualValue": 1000,
    "type": 1,
    "status": 1,
    "seckillVoucherVO": {
        "voucherId": 100001,
        "stock": 100,
        "beginTime": "2025-09-01T21:12:15",
        "endTime": "2026-09-01T21:12:15"
    }
}
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response<Void> addVoucher(@RequestBody VoucherEntity voucher){
        voucherService.addVoucher(voucher);
        return Response.<Void>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .build();

    }



}
