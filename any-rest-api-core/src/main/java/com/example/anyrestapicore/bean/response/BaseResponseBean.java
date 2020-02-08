package com.example.anyrestapicore.bean.response;

import com.example.anyrestapicore.bean.response.errors.ErrorBean;

import java.util.List;

public class BaseResponseBean<T> {

    private String statusCode;
    private String message;
    private T payload;
    private List<ErrorBean> errors;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public List<ErrorBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorBean> errors) {
        this.errors = errors;
    }
}
