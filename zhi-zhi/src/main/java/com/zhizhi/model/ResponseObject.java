package com.zhizhi.model;

/**
 * 向前端返回的json对象的包装类
 *
 * @author Yu Yang
 * @create 2020-06-13 18:20
 */
public class ResponseObject {

    private String status; // 响应状态

    private String msg; // 响应内容

    public ResponseObject() {
    }

    public ResponseObject(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * toString()方法被重写成了json序列化的形式
     * @return
     */
    @Override
    public String toString() {
        return "{\"status\":\"" + status + "\",\"msg\":\"" + msg + "\"}";
    }
}
