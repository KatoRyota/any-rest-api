package com.example.anyrestapi.service.impl;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapi.service.impl.helper.AnyCalcServiceHelper;
import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiGetRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiGetResponseBean;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.request.payload.AnyCalcRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.bean.response.errors.ErrorBean;
import com.example.anyrestapicore.bean.response.payload.AnyCalcOrGetResponseBean;
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
public class AnyCalcService extends BaseService<
        BaseRequestBean<List<AnyCalcRequestBean>>,
        BaseResponseBean<List<AnyCalcOrGetResponseBean>>> {

    private static final Logger logger = LoggerFactory.getLogger(AnyCalcService.class);
    private final Environment env;
    private final AnyCalcServiceHelper helper;
    private final RestTemplate restTemplate;

    public AnyCalcService(Environment env,
                          AnyCalcServiceHelper helper,
                          RestTemplate restTemplate) {
        super();
        this.env = env;
        this.helper = helper;
        this.restTemplate = restTemplate;
    }

    @Override
    protected BaseResponseBean<List<AnyCalcOrGetResponseBean>> createResponse() {
        return new BaseResponseBean<>();
    }

    @Override
    protected boolean validate(BaseRequestBean<List<AnyCalcRequestBean>> request,
                               BaseResponseBean<List<AnyCalcOrGetResponseBean>> response) {
        return true;
    }

    @Override
    protected List<AnyDataModel> createIntermediateObject(BaseRequestBean<List<AnyCalcRequestBean>> request) {

        String url = Objects.requireNonNull(env.getProperty("mockanyrestapi.url.getany"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MockAnyRestApiBaseRequestBean<List<MockAnyRestApiGetRequestBean>> mockAnyRestApiRequest = new MockAnyRestApiBaseRequestBean<>();
        mockAnyRestApiRequest.setUserName("userName-000-0000");
        mockAnyRestApiRequest.setAuthKey("authKey-000-0000");
        List<MockAnyRestApiGetRequestBean> mockAnyRestApiRequestPayload = new ArrayList<>();
        MockAnyRestApiGetRequestBean mockAnyRestApiRequestData = new MockAnyRestApiGetRequestBean();
        mockAnyRestApiRequestData.setId("id-000-0000");
        mockAnyRestApiRequestData.setName("name-000-0000");
        mockAnyRestApiRequestData.setType("type-000-0000");
        mockAnyRestApiRequestPayload.add(mockAnyRestApiRequestData);
        mockAnyRestApiRequest.setPayload(mockAnyRestApiRequestPayload);

        ResponseEntity<MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>>> mockAnyRestApiResponse = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(mockAnyRestApiRequest, headers),
                new ParameterizedTypeReference<>() {
                });

        MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>> mockAnyRestApiResponseBody = Objects.requireNonNull(mockAnyRestApiResponse.getBody());
        List<MockAnyRestApiGetResponseBean> mockAnyRestApiResponsePayload = mockAnyRestApiResponseBody.getPayload();
        List<AnyDataModel> anyDataModelList = new ArrayList<>();
        AnyDataModel anyDataModel = new AnyDataModel();
        MockAnyRestApiGetResponseBean mockAnyRestApiResponseData = mockAnyRestApiResponsePayload.get(0);
        anyDataModel.id = mockAnyRestApiResponseData.getId();
        anyDataModel.name = mockAnyRestApiResponseData.getName();
        anyDataModel.type = mockAnyRestApiResponseData.getType();
        anyDataModelList.add(anyDataModel);

        return anyDataModelList;
    }

    @Override
    protected void execute(BaseRequestBean<List<AnyCalcRequestBean>> request,
                           BaseResponseBean<List<AnyCalcOrGetResponseBean>> response,
                           List<AnyDataModel> anyDataModels) {

        AnyDataModel anyDataModel = anyDataModels.get(0);

        response.setStatusCode("statusCode-000-0000");
        response.setMessage("message-000-0000");

        List<AnyCalcOrGetResponseBean> anyCalcOrGetResponseBeanList = new ArrayList<>();
        AnyCalcOrGetResponseBean anyCalcOrGetResponseBean = new AnyCalcOrGetResponseBean();
        anyCalcOrGetResponseBean.setId(anyDataModel.id);
        anyCalcOrGetResponseBean.setName(anyDataModel.name);
        anyCalcOrGetResponseBean.setType(anyDataModel.type);
        anyCalcOrGetResponseBeanList.add(anyCalcOrGetResponseBean);
        response.setPayload(anyCalcOrGetResponseBeanList);

        List<ErrorBean> errors = new ArrayList<>();
        ErrorBean error = new ErrorBean();
        error.setBindId("bindId-000-0000");
        error.setCode("code-000-0000");
        error.setMessage("message-000-0000");
        errors.add(error);
        response.setErrors(errors);
    }
}
