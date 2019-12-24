package com.study.socket;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * socket請求
 * @author YCKJ1409
 */
@Data
public class SocketRequest {
    /**
     * 请求头 header
     */
    @JSONField(name = "Header")
    private SocketRequestHeader socketRequestHeader;

    /**
     * 请求体 body
     */
    @JSONField(name ="Body")
    private SocketRequestBody socketRequestBody;
}
