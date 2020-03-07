package cn.calfgz.springcloud.common.rest;


/**
 * 封装结果
 *
 * @author zhongwm
 * @create 2018-11-20 13:57
 */
public class CommonResponse {

    public static CommonResult okRsp() {
        return new CommonResult(CommonCode.SUCCSEE, null);
    }

    public static <T> CommonResult<T> okRsp(T data) {
        return new CommonResult(CommonCode.SUCCSEE, data);
    }

    public static CommonResult okRspMsg(String msg) {
        return new CommonResult(CommonCode.SUCCSEE.getCode(), msg);
    }

    public static CommonResult errRsp(String msg) {
        return new CommonResult(CommonCode.ERROR_RESULT.getCode(), msg);
    }

    public static CommonResult validRsp(String msg) {
        return new CommonResult(CommonCode.VALIDATOR_FAILED.getCode(), msg);
    }

    public static CommonResult rsp(CommonCode commonCode) {
        return new CommonResult(commonCode, null);
    }

    public static <T> CommonResult<T> rsp(CommonCode commonCode, T data) {
        return new CommonResult(commonCode, data);
    }

    public static <T> CommonResult<T> rsp(int code, String msg, T data) {
        return new CommonResult(code, msg, data);
    }

    public static CommonResult rsp(int code, String msg) {
        return new CommonResult(code, msg);
    }
}
