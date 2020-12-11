package com.study.tool;

public class CmbIncomingCallBackDto {
    private String version;
    private String charset;
    private String sign;
    private CmbIncomingCallBackDto.NoticeData noticeData;

    public CmbIncomingCallBackDto() {
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public CmbIncomingCallBackDto.NoticeData getNoticeData() {
        return this.noticeData;
    }

    public void setNoticeData(CmbIncomingCallBackDto.NoticeData noticeData) {
        this.noticeData = noticeData;
    }

    public class NoticeData {
        private String dateTime;
        private String rspCode;
        private String rspMsg;
        private String noticeType;
        private String noticeSerialNo;
        private String branchNo;
        private String merchantNo;
        private String noticeUrl;
        private String httpMethod;
        private String merchantSerialNo;
        private String bankSerialNo;
        private String agrNo;
        private String amount;
        private String settleDate;

        public NoticeData() {
        }

        public String getDateTime() {
            return this.dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getRspCode() {
            return this.rspCode;
        }

        public void setRspCode(String rspCode) {
            this.rspCode = rspCode;
        }

        public String getRspMsg() {
            return this.rspMsg;
        }

        public void setRspMsg(String rspMsg) {
            this.rspMsg = rspMsg;
        }

        public String getNoticeType() {
            return this.noticeType;
        }

        public void setNoticeType(String noticeType) {
            this.noticeType = noticeType;
        }

        public String getNoticeSerialNo() {
            return this.noticeSerialNo;
        }

        public void setNoticeSerialNo(String noticeSerialNo) {
            this.noticeSerialNo = noticeSerialNo;
        }

        public String getBranchNo() {
            return this.branchNo;
        }

        public void setBranchNo(String branchNo) {
            this.branchNo = branchNo;
        }

        public String getMerchantNo() {
            return this.merchantNo;
        }

        public void setMerchantNo(String merchantNo) {
            this.merchantNo = merchantNo;
        }

        public String getNoticeUrl() {
            return this.noticeUrl;
        }

        public void setNoticeUrl(String noticeUrl) {
            this.noticeUrl = noticeUrl;
        }

        public String getHttpMethod() {
            return this.httpMethod;
        }

        public void setHttpMethod(String httpMethod) {
            this.httpMethod = httpMethod;
        }

        public String getMerchantSerialNo() {
            return this.merchantSerialNo;
        }

        public void setMerchantSerialNo(String merchantSerialNo) {
            this.merchantSerialNo = merchantSerialNo;
        }

        public String getBankSerialNo() {
            return this.bankSerialNo;
        }

        public void setBankSerialNo(String bankSerialNo) {
            this.bankSerialNo = bankSerialNo;
        }

        public String getAgrNo() {
            return this.agrNo;
        }

        public void setAgrNo(String agrNo) {
            this.agrNo = agrNo;
        }

        public String getAmount() {
            return this.amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getSettleDate() {
            return this.settleDate;
        }

        public void setSettleDate(String settleDate) {
            this.settleDate = settleDate;
        }
    }
}
