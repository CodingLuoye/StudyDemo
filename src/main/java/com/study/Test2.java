package com.study;

import com.study.vo.ItemVo;

import java.util.concurrent.atomic.AtomicInteger;

public class Test2 {

    /**
     * 指定文字转换
     */
    public static String replaceText(String str) {
        if(str.contains("会员卡余额不足且未开通小额免密协议")){
            str = "支付账户未成功绑定，请确认";
        }else if(str.contains("会员账户信息不存在")){
            str = "会员账户信息不存在，请联系食堂管理员";
        }else if(str.contains("20002001") || str.contains("4022") || str.contains("4318") || str.contains("6422") ||  str.contains("6853")
                ||  str.contains("93008597") || str.contains("93014024") || str.contains("93014266") || str.contains(
                "93015462")){
            str = "绑定的支付账户异常，请联系食堂管理员";
        }else if(str.contains("4101") || str.contains("4102") || str.contains("8313") || str.contains("96314364")){
            str = "绑定的支付账户余额不足，请确认";
        }else if(str.contains("96314377") || str.contains("96314389")){
            str = "绑定的支付账户非工行卡，请确认";
        }else if(str.contains("用户无权限在该时间段或该商户消费")){
            str = "用户无权限在该时间段或该商户消费";
        }else if(str.contains("用户在当前时间段内消费次数超过会员卡消费次数上限")){
            str = "餐次已用尽";
        }else{
            str = "绑定的支付账户异常，请联系食堂管理员";
        }
        return str;
    }

    public static void main(String[] args) {
        String name = "用户无权限在该时间段或该商户消费";
        System.out.println(replaceText(name));

        AtomicInteger integer = new AtomicInteger(1);
        System.out.println(integer.getAndIncrement());

        ItemVo<String> itemVo = null;
        try {
            itemVo.getData();
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
