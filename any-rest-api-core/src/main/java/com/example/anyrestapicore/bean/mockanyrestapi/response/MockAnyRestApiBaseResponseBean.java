package com.example.anyrestapicore.bean.mockanyrestapi.response;


import com.example.anyrestapicore.bean.mockanyrestapi.response.errors.MockAnyRestApiErrorBean;

import java.util.List;

public class MockAnyRestApiBaseResponseBean<T> {

    private String statusCode;
    private String message;
    private T payload;
    private List<MockAnyRestApiErrorBean> errors;

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

    public List<MockAnyRestApiErrorBean> getErrors() {
        return errors;
    }

    public void setErrors(List<MockAnyRestApiErrorBean> errors) {
        this.errors = errors;
    }
}
