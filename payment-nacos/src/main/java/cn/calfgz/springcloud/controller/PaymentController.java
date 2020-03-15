package cn.calfgz.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-13 18:03
 */
@RestController
@RefreshScope
public class PaymentController {

    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String get(@PathVariable("id") String id) {
        return "nacos registry. serverPort:" + serverPort + ", configInfo:" + configInfo + ", id:" + id;
    }
}
