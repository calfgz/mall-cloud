package cn.calfgz.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author calfgz
 * @description:
 * @date 2020-03-11 17:47
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_baidu",
                r -> r.path("/guonei").uri("https://news.baidu.com/guonei"))
        .route("path_route_baidu_guoji",
                r -> r.path("/guoji").uri("https://news.baidu.com/guoji"));

        return routes.build();
    }
}
