package com.study.bcasf;

public class ReversqlReq {

    private String transAmount;

    private String tranNo;

    private String terminalId;

    private String merchId;

    private String origXSposDate;

    private String origTranNo;

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getTranNo() {
        return tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public String getOrigXSposDate() {
        return origXSposDate;
    }

    public void setOrigXSposDate(String origXSposDate) {
        this.origXSposDate = origXSposDate;
    }

    public String getOrigTranNo() {
        return origTranNo;
    }

    public void setOrigTranNo(String origTranNo) {
        this.origTranNo = origTranNo;
    }
}
