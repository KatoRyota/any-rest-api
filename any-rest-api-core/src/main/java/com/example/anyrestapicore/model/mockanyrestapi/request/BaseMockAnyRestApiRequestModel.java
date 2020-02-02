package com.example.anyrestapicore.model.mockanyrestapi.request;

public class BaseMockAnyRestApiRequestModel<T> {

    private String userName;
    private String authKey;
    private T payload;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
