package com.example.domain.system;

/**
 * @author 18190
 * @Date: 2021/7/21  20:37
 * @VERSION 1.0
 */
public class SysLog {
    private String id;
    private String userName;
    private String ip;
    private String time;
    private String method;
    private String action;

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", ip='" + ip + '\'' +
                ", time='" + time + '\'' +
                ", method='" + method + '\'' +
                ", action='" + action + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
