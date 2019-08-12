package com.study.hystrix.getdata;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.study.hystrix.HttpClientUtil;
import com.study.hystrix.ProductInfo;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * 基于 Hystrix 线程池技术实现资源隔离
 * @author YCKJ1409
 * 使用HystrixObservableCommand批量获取数据
 */
public class GetProductInfosCommand extends HystrixObservableCommand<ProductInfo> {

    private String[] productIds;

    public GetProductInfosCommand(String[] productIds) {
        // 还是绑定在同一个线程池
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup"));
        this.productIds = productIds;
    }

    @Override
    protected Observable<ProductInfo> construct() {
        return Observable.create((Observable.OnSubscribe<ProductInfo>) subscriber -> {

            for (String productId : productIds) {
                // 批量获取商品数据
                String url = "http://localhost:8081/getProductInfo?productId=" + productId;
                String response = HttpClientUtil.sendGetRequest(url,"UTF-8");
                ProductInfo productInfo = JSONObject.parseObject(response, ProductInfo.class);
                subscriber.onNext(productInfo);
            }
            subscriber.onCompleted();

        }).subscribeOn(Schedulers.io());
    }
}