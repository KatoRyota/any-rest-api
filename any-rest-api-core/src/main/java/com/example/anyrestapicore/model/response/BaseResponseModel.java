package com.example.anyrestapicore.model.response;

import com.example.anyrestapicore.model.response.errors.ErrorModel;

import java.util.List;

public class BaseResponseModel<T> {

    private String status;
    private String message;
    private T payload;
    private List<ErrorModel> errors;

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

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }
}
