package com.study.netty.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author YCKJ1409
 * 充值接口请求参数
 */
@XmlRootElement(name = "data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XMLRequest {

    /**
     * 交易ID
     */
    private String tranId;
    /**
     * 用户号
     */
    private String cusno;
    /**
     * 缴费金额
     */
    private String amount;

    /**
     * 银行流水
     */
    private String traceno;

    @XmlElement(name = "tran_id")
    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }
}
