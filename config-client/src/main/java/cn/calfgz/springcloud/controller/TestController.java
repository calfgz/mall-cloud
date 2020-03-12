package cn.calfgz.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-12 16:43
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${app.info.name}")
    private String appInfoName;

    @GetMapping("/info")
    public String info() {
        return appInfoName;
    }
}
