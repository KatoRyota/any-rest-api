package com.example.anyrestapicore.model.mockanyrestapi.response;


import com.example.anyrestapicore.model.mockanyrestapi.response.errors.MockAnyRestApiErrorModel;

import java.util.List;

public class BaseMockAnyRestApiResponseModel<T> {

    public String status;
    public String message;
    public T payload;
    public List<MockAnyRestApiErrorModel> errors;
}
