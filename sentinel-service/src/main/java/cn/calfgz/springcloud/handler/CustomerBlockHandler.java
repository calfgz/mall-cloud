package cn.calfgz.springcloud.handler;

import cn.calfgz.springcloud.common.rest.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-19 16:26
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444, "客户自定义，global handlerException---1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "客户自定义，global handlerException---2");
    }
}
