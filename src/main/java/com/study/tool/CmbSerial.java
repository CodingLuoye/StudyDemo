package com.study.tool;
import java.io.Serializable;

/**
 * 一网通流水对象
 * 对应CW_PAY_CMB_SERIAL
 *
 * @author 曲文强
 * @date 2018/8/14
 */
public class CmbSerial implements Serializable {

    private String createUserId;

    private Long createTime;

    private String lastUpdateUserId;

    private Long lastUpdateTime;

    private String id;

    private Long tradingDateTime;

    private String bankSerialNum;

    private String accountName;

    private String accountId;

    private String phoneNumber;

    private String merchantCode;

    private String clientCode;

    private Integer tradingAmount;

    private Integer afterAllowanceAmount;

    /**
     * 交易类型码
     * 0：消费
     * 1：退款
     * 2：离线消费
     */
    private Integer tradeType;

    private String tradeTypeName;

    private Integer tradeStatus;

    private String tradeStatusName;

    private String orgId;

    private String masterId;

    private String clientSerialNum;

    private String agrNo;

    private Short mealType;

    private String remark;

    private String virtualSerialNum;

    private Integer serialType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTradingDateTime() {
        return tradingDateTime;
    }

    public void setTradingDateTime(Long tradingDateTime) {
        this.tradingDateTime = tradingDateTime;
    }

    public String getBankSerialNum() {
        return bankSerialNum;
    }

    public void setBankSerialNum(String bankSerialNum) {
        this.bankSerialNum = bankSerialNum;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public Integer getTradingAmount() {
        return tradingAmount;
    }

    public void setTradingAmount(Integer tradingAmount) {
        this.tradingAmount = tradingAmount;
    }

    public Integer getAfterAllowanceAmount() {
        return afterAllowanceAmount;
    }

    public void setAfterAllowanceAmount(Integer afterAllowanceAmount) {
        this.afterAllowanceAmount = afterAllowanceAmount;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeTypeName() {
        return tradeTypeName;
    }

    public void setTradeTypeName(String tradeTypeName) {
        this.tradeTypeName = tradeTypeName;
    }

    public Integer getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeStatusName() {
        return tradeStatusName;
    }

    public void setTradeStatusName(String tradeStatusName) {
        this.tradeStatusName = tradeStatusName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getClientSerialNum() {
        return clientSerialNum;
    }

    public void setClientSerialNum(String clientSerialNum) {
        this.clientSerialNum = clientSerialNum;
    }

    public String getAgrNo() {
        return agrNo;
    }

    public void setAgrNo(String agrNo) {
        this.agrNo = agrNo;
    }

    public Short getMealType() {
        return mealType;
    }

    public void setMealType(Short mealType) {
        this.mealType = mealType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVirtualSerialNum() {
        return virtualSerialNum;
    }

    public void setVirtualSerialNum(String virtualSerialNum) {
        this.virtualSerialNum = virtualSerialNum;
    }

    public Integer getSerialType() {
        return serialType;
    }

    public void setSerialType(Integer serialType) {
        this.serialType = serialType;
    }
}