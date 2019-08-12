package com.study.hystrix.semaphore;

import com.netflix.hystrix.HystrixCommand;
import com.study.hystrix.ProductInfo;
import com.study.hystrix.timeout.GetProductInfoCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SemaphoreController {

    @RequestMapping("/getCityName")
    @ResponseBody
    public String getCityName(Long productId) {
//        HystrixCommand<ProductInfo> getProductInfoCommand = new GetProductInfoCommand(productId);
//        // 通过command执行，获取最新商品数据
//        ProductInfo productInfo = getProductInfoCommand.execute();
        Long cityId = 1L;
        GetCityNameCommand getCityNameCommand = new GetCityNameCommand(cityId);
        // 获取本地内存(cityName)的代码会被信号量进行资源隔离
        String cityName = getCityNameCommand.execute();
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCityName(cityName);
        System.out.println(productInfo);
        return "success";
    }
}
