package com.gzc.domain.voucher.service;

import com.gzc.domain.voucher.adapter.repository.IVoucherRepository;
import com.gzc.domain.voucher.model.entity.VoucherEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoucherService implements IVoucherService{

    private final IVoucherRepository voucherRepository;

    @Override
    public void addVoucher(VoucherEntity voucher) {
        voucherRepository.addVoucher(voucher);
    }
}
