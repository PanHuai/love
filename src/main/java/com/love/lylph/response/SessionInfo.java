package com.love.lylph.response;

import java.io.Serializable;

/**
 * @author PanHuai
 * @data 2018/8/6 10:42
 */
public class SessionInfo implements Serializable {

    private String key;

    private Long expiryTime;

    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String toString() {
        return new StringBuilder().append("{key: ").append(key).append(", expiryTime: ").append(expiryTime)
                .append(", value: ").append(value).append("}").toString();
    }
}
