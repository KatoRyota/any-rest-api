package com.example.anyrestapicore.model.mockanyrestapi.response.errors;

public class MockAnyRestApiErrorModel {

    private String code;
    private String bindId;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
