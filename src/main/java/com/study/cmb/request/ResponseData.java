package com.study.cmb.request;

/**
 * @author YCKJ1409
 * 招行相应model
 */
public class ResponseData {

    /**
     * 处理结果
     */
    private String rspCode;
    /**
     * 详细信息
     */
    private String rspMsg;

    /**
     * 用Base64编码的招行公钥
     */
    private String fbPubKey;

    /**
     * 响应时间,银行返回该数据的时间
     * 格式：yyyyMMddHHmmss
     */
    private String dateTime;

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public String getFbPubKey() {
        return fbPubKey;
    }

    public void setFbPubKey(String fbPubKey) {
        this.fbPubKey = fbPubKey;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "rspCode='" + rspCode + '\'' +
                ", rspMsg='" + rspMsg + '\'' +
                ", fbPubKey='" + fbPubKey + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
