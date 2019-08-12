package com.study.hystrix.timeout;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.*;
import com.study.hystrix.HttpClientUtil;
import com.study.hystrix.ProductInfo;

/**
 * @author YCKJ1409
 * 超时的降级
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfo> {

    private Long productId;

    private static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey("GetProductInfoCommand");

    public GetProductInfoCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductInfoService"))
                .andCommandKey(KEY)
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(8)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(8))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(true)
                        .withCircuitBreakerRequestVolumeThreshold(20)
                        .withCircuitBreakerErrorThresholdPercentage(40)
                        .withCircuitBreakerSleepWindowInMilliseconds(3000)
                        // 设置是否打开超时，默认是true
                        .withExecutionTimeoutEnabled(true)
                        // 设置超时时间，默认1000(ms)
                        .withExecutionTimeoutInMilliseconds(500)
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(30)));
        this.productId = productId;
    }

    @Override
    protected ProductInfo run() throws Exception {
        System.out.println("调用接口查询商品数据，productId=" + productId);

        // 休眠1s
        Thread.sleep(1000);

        String url = "http://localhost:8081/getProductInfoCache?productId=" + productId;
        String response = HttpClientUtil.sendGetRequest(url,"UTF-8");
        System.out.println(response);
        return JSONObject.parseObject(response, ProductInfo.class);
    }

    @Override
    protected ProductInfo getFallback() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setBrandName("降级商品");
        return productInfo;
    }
}