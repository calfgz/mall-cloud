package cn.calfgz.springcloud.controller;

import cn.calfgz.springcloud.common.rest.CommonResponse;
import cn.calfgz.springcloud.common.rest.CommonResult;
import cn.calfgz.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-10 13:22
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    PaymentService paymentService;

    @GetMapping("/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return CommonResponse.okRsp(serverPort + ", " + paymentService.get(id));
        //return CommonResponse.okRsp( "port:" + serverPort + ", id:" + id + ", uuid:" + UUID.randomUUID());
    }

    @GetMapping("/payment/timeout")
    public CommonResult timeout() {
        return CommonResponse.okRsp(serverPort + ", " + paymentService.timeout());
    }

    //服务熔断
    @GetMapping("/payment/circuit/{id}")
    public CommonResult circuitBreaker(@PathVariable("id") Integer id) {
        String reslut = paymentService.paymentCircuitBreaker(id);
        log.info("Thread:{}, result:{}", Thread.currentThread().getName(), reslut);
        return CommonResponse.okRsp(reslut);
    }
}
