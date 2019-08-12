package com.study.hystrix.requestcache;

import com.study.hystrix.ProductInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CacheController {

    @RequestMapping("/getProductInfoCache")
    @ResponseBody
    public ProductInfo getProductInfoCache(Long productId) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productId);
        productInfo.setBrandName("xxxx" + productId);
        productInfo.setBrandId(productId);
        return productInfo;
    }

    /**
     * 一次性批量查询多条商品数据的请求
     *
     * @param productIds 以,分隔的商品id列表
     * @return 响应状态
     */
    @RequestMapping("/getProductInfos2")
    @ResponseBody
    public String getProductInfos2(String productIds) {
        for (String productId : productIds.split(",")) {
            // 对每个productId，都创建一个command
            GetProductInfoCommand getProductInfoCommand = new GetProductInfoCommand(Long.valueOf(productId));
            ProductInfo productInfo = getProductInfoCommand.execute();
            System.out.println("是否是从缓存中取的结果：" + getProductInfoCommand.isResponseFromCache());
        }

        return "success";
    }

}