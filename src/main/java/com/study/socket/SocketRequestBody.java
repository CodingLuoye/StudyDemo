package com.study.socket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * socket請求Body
 * @author YCKJ1409
 */
@Data
public class SocketRequestBody implements Serializable {
    /**
     *  证件类型
     * 01－居民身份证
     * 02－临时身份证
     * 03－护照
     * 04－户口簿
     * 05－军人身份证
     * 06－武装警察身份证
     * 07－港澳台居民往来内地通行证（待数据转换完成后，此类型作废）
     * 08－外交人员身份证
     * 09－外国人居留许可证
     * 10－边民出入境通行证
     * 11－其它
     * 47－港澳居民来往内地通行证（香港）
     * 48－港澳居民来往内地通行证（澳门）
     * 49－台湾居民来往大陆通行证”
     * 54-纳税人识别号（TIN）（建立个人客户时，此证件类型不能输入）
     */
    @JsonProperty("IdType")
    private String idType;
    /**
     * 证件号 --身份证号
     */
    @JsonProperty("IdNo")
    private String idNo;

    /**
     * 姓名
     */
    @JsonProperty("Name")
    private String name;

    /**
     * 客观编号类型
     *
     * 1：学号
     * 2：行业卡卡号
     */
    @JsonProperty("CustCodeType")
    private String custCodeType;

    /**
     * 客户编号 -- 手机号
     */
    @JsonProperty("CustCode")
    private String custCode;
}
