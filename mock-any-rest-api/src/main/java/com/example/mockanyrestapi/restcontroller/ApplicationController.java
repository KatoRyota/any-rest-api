package com.example.mockanyrestapi.restcontroller;

import com.example.anyrestapicore.model.mockanyrestapi.request.BaseMockAnyRestApiRequestModel;
import com.example.anyrestapicore.model.mockanyrestapi.request.payload.MockAnyRestApiGetRequestModel;
import com.example.anyrestapicore.model.mockanyrestapi.response.BaseMockAnyRestApiResponseModel;
import com.example.anyrestapicore.model.mockanyrestapi.response.payload.MockAnyRestApiGetResponseModel;
import com.example.mockanyrestapi.service.impl.MockAnyRestApiGetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private final MockAnyRestApiGetService anyGetService;

    public ApplicationController(MockAnyRestApiGetService anyGetService) {
        this.anyGetService = anyGetService;
    }

    @PostMapping("getAny")
    public BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel> getAny(
            BaseMockAnyRestApiRequestModel<MockAnyRestApiGetRequestModel> request) {

        return this.anyGetService.process(request);
    }
}
