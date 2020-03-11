package cn.calfgz.springcloud.service;

import cn.calfgz.springcloud.common.rest.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "consul-payment-service")
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    CommonResult get(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    CommonResult timeout();
}
