package com.study.socket;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


/**
 * @author YCKJ1409
 * socket应答
 */
@Data
public class SocketResponse {

    /**
     * 响应头 header
     */
    @JSONField(name = "Header")
    private SocketResponseHeader socketResponseHeader;

    /**
     * 响应体 body
     */
    @JSONField(name ="Body")
    private SocketResponseBody socketResponseBody;

}

