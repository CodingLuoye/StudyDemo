package com.study.socket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * socket Header請求
 * @author YCKJ1409
 */
@Data
public class SocketRequestHeader implements Serializable {

    /**
     * 交易码
     */
    @JsonProperty("TranCode")
    private String trancode;

    /**
     * 发起单位
     */
    @JsonProperty("SCode")
    private String scode;
    /**
     * 目的单位 商户号
     */
    @JsonProperty("TCode")
    private String tcode;
    /**
     * 操作员号
     */
    @JsonProperty("OpNo")
    private String opno;

    /**
     * 终端编号
     */
    @JsonProperty("TermId")
    private String termid;

    /**
     * 终端编号
     */
    @JsonProperty("TranTime")
    private String trantime;

    /**
     * 交易流水号
     */
    @JsonProperty("TraceNo")
    private String traceno;
}
