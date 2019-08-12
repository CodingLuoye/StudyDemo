package com.study.hystrix.semaphore;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**semaphore 技术可以用来限流和削峰，但是不能用来对调研延迟的服务进行 timeout 和隔离。

 execution.isolation.strategy 设置为 SEMAPHORE，那么 Hystrix 就会用 semaphore 机制来替代线程池机制，
 来对依赖服务的访问进行限流。如果通过 semaphore 调用的时候，底层的网络调用延迟很严重，那么是无法 timeout 的，
 只能一直 block 住。一旦请求数量超过了 semaphore 限定的数量之后，就会立即开启限流。
 * @author YCKJ1409
 * 信号量
 */
public class GetCityNameCommand extends HystrixCommand<String> {

    private Long cityId;

    public GetCityNameCommand(Long cityId) {
        // 设置信号量隔离策略
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));

        this.cityId = cityId;
    }

    @Override
    protected String run() {
        // 需要进行信号量隔离的代码
        return LocationCache.getCityName(cityId);
    }
}