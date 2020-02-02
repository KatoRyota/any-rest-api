package com.example.anyrestapi.restcontroller;

import com.example.anyrestapi.service.impl.AnyGetService;
import com.example.anyrestapi.service.impl.AnyUpdateService;
import com.example.anyrestapicore.model.request.BaseRequestModel;
import com.example.anyrestapicore.model.request.payload.AnyGetRequestModel;
import com.example.anyrestapicore.model.request.payload.AnyUpdateRequestModel;
import com.example.anyrestapicore.model.response.BaseResponseModel;
import com.example.anyrestapicore.model.response.payload.AnyGetResponseModel;
import com.example.anyrestapicore.model.response.payload.AnyUpdateResponseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private final AnyGetService anyGetService;
    private final AnyUpdateService anyUpdateService;

    public ApplicationController(AnyGetService anyGetService, AnyUpdateService anyUpdateService) {
        this.anyGetService = anyGetService;
        this.anyUpdateService = anyUpdateService;
    }

    @PostMapping("getAny")
    public BaseResponseModel<AnyGetResponseModel> getAny(
            @RequestBody BaseRequestModel<AnyGetRequestModel> request) {

        return this.anyGetService.process(request);
    }

    @PostMapping("updateAny")
    public BaseResponseModel<AnyUpdateResponseModel> updateAny(
            @RequestBody BaseRequestModel<AnyUpdateRequestModel> request) {

        return this.anyUpdateService.process(request);
    }
}
