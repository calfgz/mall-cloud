package cn.calfgz.springcloud.service.impl;

import cn.calfgz.springcloud.dao.PaymentDao;
import cn.calfgz.springcloud.entity.Payment;
import cn.calfgz.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhongwm
 * @description:
 * @date 2020-03-07 14:43
 */
@Slf4j
@Service
public class PaymentServiceInpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
