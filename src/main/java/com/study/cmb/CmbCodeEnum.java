package com.study.cmb;

/**
 * @author YCKJ1409
 * 招行返回错误码
 */
public enum  CmbCodeEnum {
    PTFE000("PTFE000","未找到与手机号匹配的一网通支付账号{1}"),

    PTFE001("PTFE001","一网通账号下未找到可支付的银行卡{1}"),

    PTFE002("PTFE002","绑定的银行卡未开通支付功能{1}"),

    PTFE003("PTFE003","绑定的银行卡未开通支付功能{1}"),

    PTFE004("PTFE004","刷脸支付未开通{1}"),

    PTFE005("PTFE005","人脸比对未通过，支付失败{1}"),

    PTFE006("PTFE006","刷脸支付失败{1}"),

    PTFE007("PTFE007","支付扣款失败{1}"),

    PTFE008("PTFE008","刷脸支付失败，支付金额已达单笔或单日限额{1}"),

    PTFE009("PTFE009","支付扣款结果返回超时{1}"),

    WWQ1111("PTFE006","找不到对应记录");


    private String code;

    private String desc;

    CmbCodeEnum(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }
}
