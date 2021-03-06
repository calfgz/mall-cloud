package cn.calfgz.springcloud.controller;

import cn.calfgz.springcloud.common.rest.CommonResult;
import cn.calfgz.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-07 16:16
 */
@Slf4j
@RestController
public class OrderController {

    public static final String PAYMENT_URI = "http://PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URI + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URI + "/payment/get/" + id, CommonResult.class);
    }
}
