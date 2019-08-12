package com.study.hystrix.timeout;

import com.netflix.hystrix.HystrixCommand;
import com.study.hystrix.ProductInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TimeOutController {

    @RequestMapping("/getProductInfoTimeOut")
    @ResponseBody
    public String getProductInfoTimeOut(Long productId) {
        HystrixCommand<ProductInfo> getProductInfoCommand = new GetProductInfoCommand(productId);
        ProductInfo productInfo = getProductInfoCommand.execute();
        System.out.println(productInfo.toString());
        return "success";
    }

}
