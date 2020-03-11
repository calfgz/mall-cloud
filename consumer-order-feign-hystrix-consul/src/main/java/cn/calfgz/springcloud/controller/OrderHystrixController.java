package cn.calfgz.springcloud.controller;

import cn.calfgz.springcloud.common.rest.CommonResponse;
import cn.calfgz.springcloud.common.rest.CommonResult;
import cn.calfgz.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-11 13:49
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallbackMethod")
public class OrderHystrixController {
    @Autowired
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return paymentHystrixService.get(id);
    }

    /**@HystrixCommand(fallbackMethod = "timeoutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @GetMapping("/consumer/payment/timeout")
    @HystrixCommand
    public CommonResult timeout() {
        //int age = 1/0;
        return paymentHystrixService.timeout();
    }

    public CommonResult timeoutFallbackMethod() {
        return CommonResponse.validRsp("服务异常");
    }

    public CommonResult globalFallbackMethod() {
        return CommonResponse.validRsp("全局Fallback,服务异常");
    }
}
