package com.example.anyrestapicore.model.mockanyrestapi.response;


import com.example.anyrestapicore.model.mockanyrestapi.response.errors.MockAnyRestApiErrorModel;

import java.util.List;

public class BaseMockAnyRestApiResponseModel<T> {

    private String status;
    private String message;
    private T payload;
    private List<MockAnyRestApiErrorModel> errors;

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

    public List<MockAnyRestApiErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<MockAnyRestApiErrorModel> errors) {
        this.errors = errors;
    }
}
