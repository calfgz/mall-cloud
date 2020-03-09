package cn.calfgz.springcloud.common.rest;


/**
 * 返回结果类型
 *
 * @author calfgz
 * @create 2018-11-20 11:46
 */
public enum CommonCode {
    SUCCSEE(200, "OK"),
    DATA_EMPTY(0, "空数据"),
    VALIDATOR_FAILED(1, "验证失败"),
    ERROR_RESULT(1, "服务异常"),
    BAD_REQUEST(400, "错误请求"),
    UNAUTHORIZED(401, "授权失败"),
    NOT_FOUND(404, "数据不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法有误"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    SERVER_ERROR(500, "服务异常"),
    UNKNOW_ERROR(512,"未知异常"),
    MISS_PARAM(400, "缺少请求参数"),
    ORDER_ERROR(9, "生成订单失败"),
    UNEXPECTED(999, "异常"),
    BAD_PARAM(400, "请求参数类型有误"),
    CART_CONFIRM_ERROR(10001, "部分商品库存不足.");

    private int code;
    private String msg;

    CommonCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
