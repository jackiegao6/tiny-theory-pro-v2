package com.gzc.infrastructure.adapter.repository;

import com.gzc.domain.voucher.adapter.repository.IVoucherRepository;
import com.gzc.domain.voucher.model.entity.VoucherEntity;
import com.gzc.infrastructure.dao.ISecKillVoucherDao;
import com.gzc.infrastructure.dao.IVoucherDao;
import com.gzc.infrastructure.dao.po.SeckillVoucher;
import com.gzc.infrastructure.dao.po.Voucher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
public class VoucherRepository implements IVoucherRepository {

    private final IVoucherDao voucherDao;
    private final ISecKillVoucherDao secKillVoucherDao;

    @Transactional
    @Override
    public void addVoucher(VoucherEntity voucher) {
        Short type = voucher.getType();
        Voucher voucherReq = Voucher.builder()
                .id(voucher.getId())
                .shopId(voucher.getShopId())
                .title(voucher.getTitle())
                .subTitle(voucher.getSubTitle())
                .rules(voucher.getRules())
                .payValue(voucher.getPayValue())
                .actualValue(voucher.getActualValue())
                .type(type)
                .status(voucher.getStatus())
                .build();
        voucherDao.insert(voucherReq);
        if (type == 1){
            // 说明优惠券时秒杀券
            VoucherEntity.SeckillVoucherVO seckillVoucherVO = voucher.getSeckillVoucherVO();
            SeckillVoucher seckillVoucher = SeckillVoucher.builder()
                        .voucherId(seckillVoucherVO.getVoucherId())
                        .stock(seckillVoucherVO.getStock())
                        .beginTime(seckillVoucherVO.getBeginTime())
                        .endTime(seckillVoucherVO.getEndTime())
                        .build();
            secKillVoucherDao.insert(seckillVoucher);
        }
    }
}
