package cn.calfgz.springcloud.service;

import cn.calfgz.springcloud.common.rest.CommonResponse;
import cn.calfgz.springcloud.common.rest.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-11 15:08
 */
@Component
public class PaymentHystrixFallbackSercice implements PaymentHystrixService {
    @Override
    public CommonResult get(Long id) {
        return CommonResponse.okRsp("class:" + this.getClass().getName() + ", method: get, id:" + id );
    }

    @Override
    public CommonResult timeout() {
        return CommonResponse.okRsp("class:" + this.getClass().getName() + ", method: timeout" );
    }
}
