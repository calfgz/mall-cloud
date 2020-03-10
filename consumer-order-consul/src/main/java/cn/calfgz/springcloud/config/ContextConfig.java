package cn.calfgz.springcloud.config;

import cn.calfgz.loadbalancer.MyRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-10 13:29
 */
@Configuration
public class ContextConfig {

    /**
     * 全局使用随机负载均衡算法
     * @return
    @Bean
    public RandomRule randomRule() {
        return new RandomRule();
    }
     */

    /**
     * 全局使用自定义负载均衡算法
     * @return
     */
    @Bean
    public MyRule myRule() {
        return new MyRule();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
