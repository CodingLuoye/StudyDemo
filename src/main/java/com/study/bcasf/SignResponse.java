package com.study.bcasf;

/**
 * 签到请求
 * @author YCKJ1409
 *
 */
public class SignResponse {

    private String merchId ;

    private String terminalId;

    private String zek_dek;

    private String zek_kcv;

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getZek_dek() {
        return zek_dek;
    }

    public void setZek_dek(String zek_dek) {
        this.zek_dek = zek_dek;
    }

    public String getZek_kcv() {
        return zek_kcv;
    }

    public void setZek_kcv(String zek_kcv) {
        this.zek_kcv = zek_kcv;
    }
}
