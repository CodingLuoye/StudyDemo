package com.study.cmb.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author YCKJ1409
 * 招行查询公钥api返回model
 */
public class JsonResponse {

    private String version;

    private String charset;

    private String sign;

    private String signType;

    private ResponseData rspData;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public ResponseData getRspData() {
        return rspData;
    }

    public void setRspData(ResponseData rspData) {
        this.rspData = rspData;
    }
}
