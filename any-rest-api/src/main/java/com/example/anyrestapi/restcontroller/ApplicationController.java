package com.example.anyrestapi.restcontroller;

import com.example.anyrestapi.service.impl.AnyCalcService;
import com.example.anyrestapi.service.impl.AnyGetService;
import com.example.anyrestapi.service.impl.AnyUpdateService;
import com.example.anyrestapi.service.impl.mock.MockAnyCalcService;
import com.example.anyrestapi.service.impl.mock.MockAnyGetService;
import com.example.anyrestapi.service.impl.mock.MockAnyUpdateService;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.request.payload.AnyCalcRequestBean;
import com.example.anyrestapicore.bean.request.payload.AnyUpdateRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.bean.response.payload.AnyCalcOrGetResponseBean;
import com.example.anyrestapicore.bean.response.payload.AnyUpdateResponseBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicationController {

    private final AnyCalcService anyCalcService;
    private final AnyGetService anyGetService;
    private final AnyUpdateService anyUpdateService;
    private final MockAnyCalcService mockAnyCalcService;
    private final MockAnyGetService mockAnyGetService;
    private final MockAnyUpdateService mockAnyUpdateService;

    public ApplicationController(
            AnyCalcService anyCalcService,
            AnyGetService anyGetService,
            AnyUpdateService anyUpdateService,
            MockAnyCalcService mockAnyCalcService,
            MockAnyGetService mockAnyGetService,
            MockAnyUpdateService mockAnyUpdateService) {

        this.anyCalcService = anyCalcService;
        this.anyGetService = anyGetService;
        this.anyUpdateService = anyUpdateService;
        this.mockAnyCalcService = mockAnyCalcService;
        this.mockAnyGetService = mockAnyGetService;
        this.mockAnyUpdateService = mockAnyUpdateService;
    }

    @PostMapping("calcAny")
    public BaseResponseBean<List<AnyCalcOrGetResponseBean>> calcAny(
            @RequestBody BaseRequestBean<List<AnyCalcRequestBean>> request) {

        return this.anyCalcService.process(request);
    }

    @PostMapping("getAny")
    public BaseResponseBean<List<AnyCalcOrGetResponseBean>> getAny(
            @RequestBody BaseRequestBean<List<String>> request) {

        return this.anyGetService.process(request);
    }

    @PostMapping("updateAny")
    public BaseResponseBean<AnyUpdateResponseBean> updateAny(
            @RequestBody BaseRequestBean<List<AnyUpdateRequestBean>> request) {

        return this.anyUpdateService.process(request);
    }

    @PostMapping("mockCalcAny")
    public BaseResponseBean<List<AnyCalcOrGetResponseBean>> mockCalcAny(
            @RequestBody BaseRequestBean<List<AnyCalcRequestBean>> request) {

        return this.mockAnyCalcService.process(request);
    }

    @PostMapping("mockGetAny")
    public BaseResponseBean<List<AnyCalcOrGetResponseBean>> mockGetAny(
            @RequestBody BaseRequestBean<List<String>> request) {

        return this.mockAnyGetService.process(request);
    }

    @PostMapping("mockUpdateAny")
    public BaseResponseBean<AnyUpdateResponseBean> mockUpdateAny(
            @RequestBody BaseRequestBean<List<AnyUpdateRequestBean>> request) {

        return this.anyUpdateService.process(request);
    }
}
