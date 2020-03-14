package cn.calfgz.springcloud.controller;

import cn.calfgz.springcloud.common.rest.CommonResponse;
import cn.calfgz.springcloud.common.rest.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-13 18:20
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String paymentServerURL;

    @GetMapping("/consumer/payment/nacos/{id}")
    public CommonResult get(@PathVariable("id") String id) {
        return CommonResponse.okRsp(restTemplate.getForObject(paymentServerURL + "/payment/nacos/" + id, String.class));
    }
}
