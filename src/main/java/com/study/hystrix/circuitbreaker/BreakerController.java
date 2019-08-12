package com.study.hystrix.circuitbreaker;

import com.netflix.hystrix.HystrixCommand;
import com.study.hystrix.ProductInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author YCKJ1409
 * 断路器
 */
@Controller
public class BreakerController {

    @RequestMapping("/getBreakerProductInfo")
    @ResponseBody
    public String getBreakerProductInfo(Long productId) {
        HystrixCommand<ProductInfo> getProductInfoCommand = new GetProductInfoCommand(productId);

        // 通过command执行，获取最新商品数据
        ProductInfo productInfo = getProductInfoCommand.execute();
        System.out.println(productInfo);
        return "success";
    }

}
