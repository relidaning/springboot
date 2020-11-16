package com.lidaning.springboot.common;


public class CommonResult<T> {
    private int code;
    private String message;
    private T data;

    public CommonResult(int code, String msg, T t) {
        this.code=code;
        this.message=msg;
        this.data=t;
    }

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
}
