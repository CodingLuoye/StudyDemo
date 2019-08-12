package com.study.cmb.request;

/**
 * reqData model
 * @author YCKJ1409
 *
 */
public class RequestData {

    /**
     * 商户发起该请求的当前时间，精确到秒
     * 格式：yyyyMMddHHmmss
     */
    private String dateTime;
    /**
     * 交易码,固定为“FBPK”
     */
    private String txCode;

    /**
     * 	商户分行号，4位数字
     */
    private String branchNo;

    /**
     * 商户号，6位数字
     */
    private String merchantNo;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTxCode() {
        return txCode;
    }

    public RequestData(){

    }

    public RequestData(String dateTime, String txCode, String branchNo, String merchantNo) {
        this.dateTime = dateTime;
        this.txCode = txCode;
        this.branchNo = branchNo;
        this.merchantNo = merchantNo;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "dateTime='" + dateTime + '\'' +
                ", txCode='" + txCode + '\'' +
                ", branchNo='" + branchNo + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                '}';
    }
}
