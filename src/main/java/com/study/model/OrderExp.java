package com.study.model;

import java.util.Date;
/**
 * @author YCKJ1409
 */
public class OrderExp {

    private Long id;

    private String orderNo;

    private String orderNote;

    private Date insertTime;

    private Long expireDuration;

    private Date expireTime;

    private Short orderStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Long getExpireDuration() {
        return expireDuration;
    }

    public void setExpireDuration(Long expireDuration) {
        this.expireDuration = expireDuration;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderExp(){

    }

    public OrderExp(Long id, String orderNo, String orderNote, Date insertTime, Long expireDuration, Date expireTime, Short orderStatus) {
        this.id = id;
        this.orderNo = orderNo;
        this.orderNote = orderNote;
        this.insertTime = insertTime;
        this.expireDuration = expireDuration;
        this.expireTime = expireTime;
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderExp{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", orderNote='" + orderNote + '\'' +
                ", insertTime=" + insertTime +
                ", expireDuration=" + expireDuration +
                ", expireTime=" + expireTime +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
