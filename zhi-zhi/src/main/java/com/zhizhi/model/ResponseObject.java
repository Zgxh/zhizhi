package com.zhizhi.model;

/**
 * 向前端返回的json对象的包装类
 *
 * @author Yu Yang
 * @create 2020-06-13 18:20
 */
public class ResponseObject {

    private String status; // 响应状态

    private String message; // 响应内容

    public ResponseObject() {
    }

    public ResponseObject(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}