package com.study.bcasf;

/**
 * @author YCKJ1409
 * @param <T>
 */
public class ResponseData<T> {
    private String retCode;
    private String retMsg;
    private T data;

    public ResponseData(String retCode, String retMsg, T data) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.data = data;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}