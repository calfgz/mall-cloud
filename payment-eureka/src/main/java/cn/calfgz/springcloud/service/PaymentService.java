package cn.calfgz.springcloud.service;

import cn.calfgz.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
