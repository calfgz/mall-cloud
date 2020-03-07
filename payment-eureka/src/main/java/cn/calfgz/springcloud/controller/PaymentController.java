package cn.calfgz.springcloud.controller;

import cn.calfgz.springcloud.common.rest.CommonResponse;
import cn.calfgz.springcloud.common.rest.CommonResult;
import cn.calfgz.springcloud.entity.Payment;
import cn.calfgz.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhongwm
 * @description:
 * @date 2020-03-07 14:46
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @Resource
    DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("create param:{payment = [" + payment + "]}: result:{}", result);
        if (result > 0) {
            return CommonResponse.okRsp(result);
        } else {
            return CommonResponse.errRsp("插入失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("getPaymentById param:{id = [" + id + "]}: payment:{}", payment);
        if (payment != null) {
            return CommonResponse.okRsp(payment);
        } else {
            return CommonResponse.errRsp("空数据");
        }
    }

    @GetMapping("/payment/discovery")
    public CommonResult discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(service -> {
            log.info("service:{}", service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            instances.forEach(instance -> {
                log.info("serviceId:{},host:{},port:{},uri:{}", instance.getServiceId(), instance.getHost(),
                        instance.getPort(), instance.getUri());
            });
        });
        return CommonResponse.okRsp(discoveryClient);
    }
}
