package com.study.hystrix.interfaceHystrix;

import com.netflix.hystrix.HystrixCommand;
import com.study.hystrix.ProductInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author YCKJ1409
 */
@Controller
public class InterfaceController {

    @RequestMapping("/getInterfaceProductInfo")
    @ResponseBody
    public String getInterfaceProductInfo(Long productId) {
        HystrixCommand<ProductInfo> getProductInfoCommand = new GetProductInfoCommand(productId);

        // 通过command执行，获取最新商品数据
        ProductInfo productInfo = getProductInfoCommand.execute();
        System.out.println(productInfo);
        return "success";
    }
}
