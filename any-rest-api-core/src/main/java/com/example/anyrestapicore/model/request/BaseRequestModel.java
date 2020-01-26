package com.example.anyrestapicore.model.request;

public class BaseRequestModel<T> {

    public String userName;
    public String authKey;
    public T payload;
}
