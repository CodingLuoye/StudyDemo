package com.study.hystrix.getdata;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixObservableCommand;
import com.study.hystrix.ProductInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rx.Observable;
import rx.Observer;

/**
 * @author YCKJ1409
 */
@Controller
public class GetDataController {

    @RequestMapping("/getProductInfo")
    @ResponseBody
    public String getProductInfo(Long productId) {
        HystrixCommand<ProductInfo> getProductInfoCommand = new GetProductInfoCommand(productId);

        // 通过command执行，获取最新商品数据
        ProductInfo productInfo = getProductInfoCommand.execute();
        System.out.println(productInfo);
        return "success";
    }

    @RequestMapping("/getProductInfos")
    @ResponseBody
    public String getProductInfos(String productIds) {
        String[] productIdArray = productIds.split(",");
        HystrixObservableCommand<ProductInfo> getProductInfosCommand = new GetProductInfosCommand(productIdArray);
        Observable<ProductInfo> observable = getProductInfosCommand.observe();
        observable.subscribe(new Observer<ProductInfo>() {
            @Override
            public void onCompleted() {
                System.out.println("获取完了所有的商品数据");
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
            /**
             * 获取完一条数据，就回调一次这个方法
             * @param productInfo
             */
            @Override
            public void onNext(ProductInfo productInfo) {
                System.out.println(productInfo);
            }
        });
        return "success";
    }

    /**
     * 一次性批量查询多条商品数据的请求
     *
     * @param productIds 以,分隔的商品id列表
     * @return 响应状态
     */
    @RequestMapping("/getProductInfosNew")
    @ResponseBody
    public String getProductInfosNew(String productIds) {
        for (String productId : productIds.split(",")) {
            // 对每个productId，都创建一个command
            GetProductInfoCommand getProductInfoCommand = new GetProductInfoCommand(Long.valueOf(productId));
            ProductInfo productInfo = getProductInfoCommand.execute();
            System.out.println("是否是从缓存中取的结果：" + getProductInfoCommand.isResponseFromCache());
        }
        return "success";
    }
}
