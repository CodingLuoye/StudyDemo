package com.study.socket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author YCKJ1409
 * 响应结果
 */
@Data
public class BodyData {
    /**
     * 行业卡卡号
     */
    @JsonProperty("Cardno")
    private String cardNo;
    /**
     * 卡类型
     * 1 餐卡
     * 2 交通卡
     * 3 员工卡
     * 4 校园卡
     */
    @JsonProperty("Cardtype")
    private Integer cardType;
    /**
     * 卡介质
     * 1 实体卡
     * 2 虚拟卡
     */
    @JsonProperty("cardMedium")
    private Integer cardMedium;
    /**
     * 余额
     * 以元为单位，两位小数
     */
    @JsonProperty("Balance")
    private BigDecimal balance;
    /**
     * 挂失标识
     * 0：正常
     * 1：挂失
     */
    @JsonProperty("Lostflag")
    private Integer lostFlag;
    /**
     * 冻结标识
     * 0：正常
     * 1：挂失
     */
    @JsonProperty("Freezeflag")
    private Integer freezeFlag;
}
