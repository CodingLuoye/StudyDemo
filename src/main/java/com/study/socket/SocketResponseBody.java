package com.study.socket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author YCKJ1409
 * socket应答 Body
 */
@Data
public class SocketResponseBody {

    /**
     * 用户姓名
     */
    @JsonProperty("Name")
    private String name;

    /**
     * 学号
     */
    @JsonProperty("Sno")
    private String sno;

    @JsonProperty("LIST")
    private List<BodyData> list;

}

