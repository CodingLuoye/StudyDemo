package com.study.socket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author YCKJ1409
 * socket应答 header
 */
@Data
public class SocketResponseHeader {

    /**
     * 业务类型
     */
    @JsonProperty("trancode")
    private String trancode;

    /**
     * 返回结果
     */
    @JsonProperty("retcode")
    private String retcode;

    /**
     * 结果描述
     */
    @JsonProperty("retmsg")
    private String retmsg;

    /**
     * 交易流水号
     */
    @JsonProperty("traceno")
    private String traceno;

}

