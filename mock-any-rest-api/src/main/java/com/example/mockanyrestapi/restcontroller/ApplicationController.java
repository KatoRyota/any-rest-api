package com.example.mockanyrestapi.restcontroller;

import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestModel;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiGetRequestModel;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseModel;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiGetResponseModel;
import com.example.mockanyrestapi.service.impl.MockAnyRestApiGetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private final MockAnyRestApiGetService anyGetService;

    public ApplicationController(MockAnyRestApiGetService anyGetService) {
        this.anyGetService = anyGetService;
    }

    @PostMapping("getAny")
    public MockAnyRestApiBaseResponseModel<MockAnyRestApiGetResponseModel> getAny(
            @RequestBody MockAnyRestApiBaseRequestModel<MockAnyRestApiGetRequestModel> request) {

        return this.anyGetService.process(request);
    }
}
