package com.example.anyrestapicore.model.response;

import com.example.anyrestapicore.model.response.errors.ErrorModel;

import java.util.List;

public class BaseResponseModel<T> {

    public String status;
    public String message;
    public T payload;
    public List<ErrorModel> errors;
}
