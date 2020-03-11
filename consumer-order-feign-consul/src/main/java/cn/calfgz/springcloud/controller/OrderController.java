package cn.calfgz.springcloud.controller;

import cn.calfgz.springcloud.common.rest.CommonResult;
import cn.calfgz.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-10 21:52
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    PaymentService paymentService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return paymentService.get(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public CommonResult timeout(){
        return paymentService.timeout();
    }
}
