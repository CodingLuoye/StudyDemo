package com.study.cmb;

/**
 * @author YCKJ1409
 * 招行txnType枚举说明
 *
 */
public enum CmbTxnTypeCodeEnum {

    TXN_TYPE_CODE_00("00","支付预检查"),

    TXN_TYPE_CODE_01("01","刷脸支付"),

    TXN_TYPE_CODE_02("02","小n库刷脸"),

    TXN_TYPE_CODE_03("03","小n库身份确认"),

    TXN_TYPE_CODE_10("10","退款"),

    TXN_TYPE_CODE_20("20","退款"),

    TXN_TYPE_CODE_21("21","单笔退款查询"),

    TXN_TYPE_CODE_30("03","单笔退款查询");

    private String code;

    private String desc;

    CmbTxnTypeCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
