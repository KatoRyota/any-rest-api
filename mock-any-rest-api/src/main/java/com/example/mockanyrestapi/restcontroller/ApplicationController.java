package com.example.mockanyrestapi.restcontroller;

import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiGetRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiUpdateRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiGetResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiUpdateResponseBean;
import com.example.mockanyrestapi.service.impl.MockAnyRestApiGetService;
import com.example.mockanyrestapi.service.impl.MockAnyRestApiUpdateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {

    private final MockAnyRestApiGetService anyGetService;
    private final MockAnyRestApiUpdateService anyUpdateService;

    public ApplicationController(MockAnyRestApiGetService anyGetService,
                                 MockAnyRestApiUpdateService anyUpdateService) {

        this.anyGetService = anyGetService;
        this.anyUpdateService = anyUpdateService;
    }

    @PostMapping("getAny")
    public MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>> getAny(
            @RequestBody MockAnyRestApiBaseRequestBean<List<MockAnyRestApiGetRequestBean>> request) {

        return this.anyGetService.process(request);
    }

    @PostMapping("updateAny")
    public MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean> updateAny(
            @RequestBody MockAnyRestApiBaseRequestBean<List<MockAnyRestApiUpdateRequestBean>> request) {

        return this.anyUpdateService.process(request);
    }
}
