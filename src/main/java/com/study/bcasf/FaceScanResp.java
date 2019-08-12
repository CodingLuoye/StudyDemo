package com.study.bcasf;

/**
 * @author YCKJ1409
 * 响应包体明细
 */
public class FaceScanResp {

    private String respCode;

    private String respMsg;

    private String username;

    private String telNum;

    private String headingCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getHeadingCode() {
        return headingCode;
    }

    public void setHeadingCode(String headingCode) {
        this.headingCode = headingCode;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
