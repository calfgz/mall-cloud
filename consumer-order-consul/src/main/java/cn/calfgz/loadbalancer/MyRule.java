package cn.calfgz.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author calfgz
 * @description: 自定义负载均衡算法 要求：每个服务轮询访问3次
 * @date 2020-03-10 16:50
 */
@Slf4j
public class MyRule extends AbstractLoadBalancerRule {

    private AtomicInteger nextServerCyclicCounter;
    private AtomicInteger total;

    public MyRule() {
        nextServerCyclicCounter = new AtomicInteger(0);
        total = new AtomicInteger(0);
    }

    public MyRule(ILoadBalancer lb) {
        this();
        setLoadBalancer(lb);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: {}", lb);
                return null;
            }

            int nextServerIndex = incrementAndGetModulo(serverCount);
            log.warn("nextServerIndex:{}", nextServerIndex);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive() && server.isReadyToServe()) {
                return server;
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: {}", lb);
        }
        return server;
    }

    /**
     * 每个服务调用3次再切换
     * @param modulo
     * @return
     */
    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            log.warn("current:{}, next:{}, total:{}", current, next, total.get());
            if (total.incrementAndGet() < 3) {
                return current;
            } else if (nextServerCyclicCounter.compareAndSet(current, next)){
                total.set(0);
                return next;
            }
        }
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }
}
