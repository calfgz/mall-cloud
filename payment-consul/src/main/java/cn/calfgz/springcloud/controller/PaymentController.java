package cn.calfgz.springcloud.controller;

import cn.calfgz.springcloud.common.rest.CommonResponse;
import cn.calfgz.springcloud.common.rest.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-10 13:22
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return CommonResponse.okRsp( "port:" + serverPort + ", id:" + id + ", uuid:" + UUID.randomUUID());
    }
}
