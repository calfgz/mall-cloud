package cn.calfgz.springcloud.controller;

import cn.calfgz.springcloud.common.rest.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-10 13:30
 */
@RestController
public class OrderController {
    public static final String PAYMENT_URI = "http://consul-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URI + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public CommonResult zipkin() {
        return restTemplate.getForObject(PAYMENT_URI + "/payment/zipkin", CommonResult.class);
    }
}
