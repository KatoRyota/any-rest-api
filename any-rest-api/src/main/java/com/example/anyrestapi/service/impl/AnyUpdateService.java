package com.example.anyrestapi.service.impl;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapi.service.impl.helper.AnyUpdateServiceHelper;
import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiUpdateRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiUpdateResponseBean;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.request.payload.AnyUpdateRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.bean.response.errors.ErrorBean;
import com.example.anyrestapicore.bean.response.payload.AnyUpdateResponseBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AnyUpdateService extends BaseService<
        BaseRequestBean<List<AnyUpdateRequestBean>>,
        BaseResponseBean<AnyUpdateResponseBean>> {

    private static final Logger logger = LoggerFactory.getLogger(AnyUpdateService.class);
    private final Environment env;
    private final AnyUpdateServiceHelper helper;
    private final RestTemplate restTemplate;

    public AnyUpdateService(Environment env,
                            AnyUpdateServiceHelper helper,
                            RestTemplate restTemplate) {
        super();
        this.env = env;
        this.helper = helper;
        this.restTemplate = restTemplate;
    }

    @Override
    protected BaseResponseBean<AnyUpdateResponseBean> createResponse() {
        return new BaseResponseBean<>();
    }

    @Override
    protected boolean validate(BaseRequestBean<List<AnyUpdateRequestBean>> request,
                               BaseResponseBean<AnyUpdateResponseBean> response) {
        return true;
    }

    @Override
    protected List<AnyDataModel> createIntermediateObject(BaseRequestBean<List<AnyUpdateRequestBean>> request) {

        List<AnyDataModel> anyDataModelList = new ArrayList<>();
        AnyDataModel anyDataModel = new AnyDataModel();
        anyDataModel.id = "id-000-0000";
        anyDataModel.name = "name-000-0000";
        anyDataModel.type = "type-000-0000";
        anyDataModelList.add(anyDataModel);

        return anyDataModelList;
    }

    @Override
    protected void execute(BaseRequestBean<List<AnyUpdateRequestBean>> request,
                           BaseResponseBean<AnyUpdateResponseBean> response,
                           List<AnyDataModel> anyDataModels) {

        String url = Objects.requireNonNull(env.getProperty("mockanyrestapi.url.updateany"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MockAnyRestApiBaseRequestBean<List<MockAnyRestApiUpdateRequestBean>> mockAnyRestApiRequest = new MockAnyRestApiBaseRequestBean<>();
        mockAnyRestApiRequest.setUserName("userName-000-0000");
        mockAnyRestApiRequest.setAuthKey("authKey-000-0000");
        List<MockAnyRestApiUpdateRequestBean> mockAnyRestApiRequestPayload = new ArrayList<>();
        MockAnyRestApiUpdateRequestBean mockAnyRestApiRequestData = new MockAnyRestApiUpdateRequestBean();
        mockAnyRestApiRequestData.setId("id-000-0000");
        mockAnyRestApiRequestData.setName("name-000-0000");
        mockAnyRestApiRequestData.setType("type-000-0000");
        mockAnyRestApiRequestPayload.add(mockAnyRestApiRequestData);
        mockAnyRestApiRequest.setPayload(mockAnyRestApiRequestPayload);

        ResponseEntity<MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean>> mockAnyRestApiResponse = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(mockAnyRestApiRequest, headers),
                new ParameterizedTypeReference<>() {
                });

        MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean> mockAnyRestApiResponseBody = Objects.requireNonNull(mockAnyRestApiResponse.getBody());
        MockAnyRestApiUpdateResponseBean mockAnyRestApiResponsePayload = mockAnyRestApiResponseBody.getPayload();

        response.setStatusCode("statusCode-000-0000");
        response.setMessage("message-000-0000");

        AnyUpdateResponseBean anyUpdateResponseBean = new AnyUpdateResponseBean();
        anyUpdateResponseBean.setUpdateCount(mockAnyRestApiResponsePayload.getUpdateCount());
        response.setPayload(anyUpdateResponseBean);

        List<ErrorBean> errors = new ArrayList<>();
        ErrorBean error = new ErrorBean();
        error.setBindId("bindId-000-0000");
        error.setCode("code-000-0000");
        error.setMessage("message-000-0000");
        errors.add(error);
        response.setErrors(errors);
    }
}
