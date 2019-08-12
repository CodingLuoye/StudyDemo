package com.study.hystrix.localcache;

import com.netflix.hystrix.HystrixCommand;
import com.study.hystrix.ProductInfo;
import com.study.hystrix.requestcache.GetProductInfoCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocalCacheController {

    @RequestMapping("/getCacheProduct")
    @ResponseBody
    public String getCacheProduct(Long productId) {
        HystrixCommand<ProductInfo> getProductInfoCommand = new GetProductInfoCommand(productId);

        ProductInfo productInfo = getProductInfoCommand.execute();
        Long brandId = productInfo.getBrandId();

        HystrixCommand<String> getBrandNameCommand = new GetBrandNameCommand(brandId);

        // 执行会抛异常报错，然后走降级
        String brandName = getBrandNameCommand.execute();
        productInfo.setBrandName(brandName);

        System.out.println(productInfo);
        return "success";
    }
}