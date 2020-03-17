package cn.calfgz.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongwm
 * @description:
 * @date 2020-03-17 13:09
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test/a")
    @SentinelResource
    public String testa() {
        return Thread.currentThread().getName() + ", test A.";
    }

    @GetMapping("/test/b")
    @SentinelResource
    public String testb() {
        return Thread.currentThread().getName() + ", test B.";
    }

    @GetMapping("/test/c")
    @SentinelResource
    public String testc() {
        return Thread.currentThread().getName() + ", test C.";
    }

    @GetMapping("/test/d")
    public String testd() {
        return Thread.currentThread().getName() + ", test D.";
    }

}
