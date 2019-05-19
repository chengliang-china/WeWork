package com.wework.base.domain.base;

public class BaseJSON {
    protected String message="成功";
    protected int code=0;
    protected Object result;
    public BaseJSON(){
    }

    public BaseJSON(int code,String message){
        this.code=code;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setFail(){
        this.code=1;
        this.message="系统异常，请稍后重试";
    }
}
