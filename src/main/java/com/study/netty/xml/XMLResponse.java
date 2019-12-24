package com.study.netty.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author YCKJ1409
 * 充值接口返回参数
 */
@XmlRootElement(name = "data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XMLResponse {

    /**
     * 返回码
     */
    private String retcode;
    /**
     * 错误信息
     */
    private String retmsg;
    /**
     * 用户号
     */
    private String cusno;

    /**
     * 银行流水
     */
    private String cusname;

    /**
     * 金额
     */
    private String amount;

}
