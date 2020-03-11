package cn.calfgz.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-11 14:02
 */
@Service
@Slf4j
public class PaymentService {

    public String get(Long id) {
        return "tread:" + Thread.currentThread().getName() + ",ok id:" + id;
    }

    @HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String timeout() {
        //int age = 1/0;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
        return "tread:" + Thread.currentThread().getName() + ", time out.";
    }

    public String timeoutHandler() {
        return "tread:" + Thread.currentThread().getName() + ", time out handler.";
    }

    /**
     * 服务熔断
     * 涉及到断路器的三个重要参数：快照的时间窗、请求总数阀值、错误百分比阀值。
     * 1. 快照的时间窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照的时间窗，默认为最近的10秒。
     * 2. 请求总数阀值：在快照时间窗内，必须满足请求总数阀值才有资格熔断。默认为20，意味着在10秒内，如果该hystrix命令的调用次数不足20次，即使
     *    所有的请求都超时或其它原因失败，断路器都不会打开。
     * 3. 错误百分比阀值：当请求总数在快照时间窗内超过了阀值，比如10秒内发生了20次调用，如果在这30次调用中，有15次发生了超时异常，也就是超过50%
     *    的错误百分比，在默认设定50%阀值的情况下，这时候就会将熔断器打开。
     *
     * 当熔断器开启的时候，所有的请求都不会进行转发
     * 一段时间之后(默认是5秒)，这个时候断路器是半开状态，会主其中一个请求进行转发。
     * 如果成功，断路器会关闭，若失败，则继续保持开启，重复上一步流程
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            //是否开启断路器 10秒内请求超过10次且失败率达到60%时熔断
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //时间窗口期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            //失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + " 调用成功. uuid:" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id不能为负数。id:" + id;
    }
}
