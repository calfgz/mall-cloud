package cn.calfgz.springcloud.controller;

import cn.calfgz.springcloud.common.rest.CommonResponse;
import cn.calfgz.springcloud.common.rest.CommonResult;
import cn.calfgz.springcloud.handler.CustomerBlockHandler;
import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author calfgz
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
    @SentinelResource(value = "test-c", blockHandler = "handleException")
    public CommonResult testc() {
        return CommonResponse.okRsp(Thread.currentThread().getName() + ", test C.");
    }

    @GetMapping("/test/d")
    public String testd() {
        return Thread.currentThread().getName() + ", test D.";
    }

    public CommonResult handleException() {
        return CommonResponse.errRsp("block handler");
    }

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return new CommonResult(200, "按资源名称限流测试OK", IdUtil.getSnowflake(0, 1));
    }

    public CommonResult handleException(BlockException blockException){
        return new CommonResult<>(444, blockException.getClass().getCanonicalName()+"\t服务不可用" );
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200, "by url限流测试OK", IdUtil.getSnowflake(0, 1));
    }
    //CustomerBlockHandler

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException2")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200, "客户自定义 限流测试OK", IdUtil.getSnowflake(0, 1));
    }

}
