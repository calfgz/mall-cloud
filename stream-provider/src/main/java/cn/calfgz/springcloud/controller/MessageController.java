package cn.calfgz.springcloud.controller;

import cn.calfgz.springcloud.common.rest.CommonResponse;
import cn.calfgz.springcloud.common.rest.CommonResult;
import cn.calfgz.springcloud.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-13 11:43
 */
@RestController
@Slf4j
public class MessageController {

    @Resource
    MessageProvider messageProvider;

    @GetMapping("/send")
    public CommonResult send() {
        return CommonResponse.okRsp(messageProvider.send());
    }
}
