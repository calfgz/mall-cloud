package cn.calfgz.springcloud.dao;

import cn.calfgz.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author calfgz
 * @description: 支付
 * @date 2020-03-07 14:26
 */
@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
