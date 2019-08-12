package com.study.cmb.request;

/**
 * @author YCKJ1409
 * 招行查询公钥API请求实体类
 */
public class JsonRequest {

    /**
     * 接口版本号,固定为”1.0”
     */
    private String version;

    /**
     * 参数编码，固定为“UFT-8”
     */
    private String charset;

    /**
     * 报文签名,使用商户支付密钥对reqData内的数据进行签名
     */
    private String sign;

    /**
     * 	签名算法,固定为”SHA-256
     */
    private String singType;

    private RequestData reqData;

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

    public String getSingType() {
        return singType;
    }

    public void setSingType(String singType) {
        this.singType = singType;
    }

    public RequestData getReqData() {
        return reqData;
    }

    public void setReqData(RequestData reqData) {
        this.reqData = reqData;
    }

    @Override
    public String toString() {
        return "JsonRequest{" +
                "version='" + version + '\'' +
                ", charset='" + charset + '\'' +
                ", sign='" + sign + '\'' +
                ", singType='" + singType + '\'' +
                ", requestData=" + reqData +
                '}';
    }
}
