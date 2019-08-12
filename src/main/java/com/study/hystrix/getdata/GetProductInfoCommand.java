package com.study.hystrix.getdata;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.study.hystrix.HttpClientUtil;
import com.study.hystrix.ProductInfo;
/**
 * 线程池机制的优点
 * 任何一个依赖服务都可以被隔离在自己的线程池内，即使自己的线程池资源填满了，也不会影响任何其他的服务调用。
 * 服务可以随时引入一个新的依赖服务，因为即使这个新的依赖服务有问题，也不会影响其他任何服务的调用。
 * 当一个故障的依赖服务重新变好的时候，可以通过清理掉线程池，瞬间恢复该服务的调用，而如果是 tomcat 线程池被占满，再恢复就很麻烦。
 * 如果一个 client 调用库配置有问题，线程池的健康状况随时会报告，比如成功/失败/拒绝/超时的次数统计，然后可以近实时热修改依赖服务的调用配置，而不用停机。
 * 基于线程池的异步本质，可以在同步的调用之上，构建一层异步调用层。
 * 简单来说，最大的好处，就是资源隔离，确保说任何一个依赖服务故障，不会拖垮当前的这个服务。
 *
 * 线程池机制的缺点
 * 线程池机制最大的缺点就是增加了 CPU 的开销。
 * 除了 tomcat 本身的调用线程之外，还有 Hystrix 自己管理的线程池。
 *
 * 每个 command 的执行都依托一个独立的线程，会进行排队，调度，还有上下文切换。
 *
 * Hystrix 官方自己做了一个多线程异步带来的额外开销统计，通过对比多线程异步调用+同步调用得出，Netflix API 每天通过 Hystrix 执行 10 亿次调用，每个服务实例有 40 个以上的线程池，每个线程池有 10 个左右的线程。）最后发现说，用 Hystrix 的额外开销，就是给请求带来了 3ms 左右的延时，最多延时在 10ms 以内，相比于可用性和稳定性的提升，这是可以接受的。
 *
 * 我们可以用 Hystrix semaphore 技术来实现对某个依赖服务的并发访问量的限制，而不是通过线程池/队列的大小来限制流量。
 * 基于 Hystrix 线程池技术实现资源隔离
 * @author YCKJ1409
 * 使用HystrixCommand获取单条数据
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    private Long productId;

    public GetProductInfoCommand(Long productId) {
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoCommandGroup"));
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() {
        String url = "http://localhost:8081/getProductInfo?productId=" + productId;
        // 调用商品服务接口
        String response = HttpClientUtil.sendGetRequest(url,"UTF-8");
        return JSONObject.parseObject(response, ProductInfo.class);
    }
}