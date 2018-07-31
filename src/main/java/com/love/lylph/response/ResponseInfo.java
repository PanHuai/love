package com.love.lylph.response;

import java.io.Serializable;

/**
 * @author PanHuai
 * @data 2018/7/31 10:29
 */
public class ResponseInfo implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public ResponseInfo() {
    }

    public ResponseInfo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
