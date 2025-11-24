package com.mlm.mlmserver.entity;

public class Response<T> {
    private int code;            // 状态码
    private String message;        // 描述
    private T data;                // 结果

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response() {
        super();
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //	成功响应
    public Response<T> success(T data) {
        return new Response<T>(200, "成功", data);
    }

    //	失败响应
    public Response<T> error(int code, String message) {
        return new Response<T>(code, message, null);
    }

    public Response<T> error(int code, String message, T data) {
        return new Response<T>(code, message, data);
    }
}