package com.example.anyrestapi.service.impl;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapi.service.impl.helper.AnyGetServiceHelper;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiGetResponseBean;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.request.payload.AnyCalcRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
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
    private final AnyGetServiceHelper helper;
    private final RestTemplate restTemplate;

    public AnyCalcService(Environment env, AnyGetServiceHelper helper, RestTemplate restTemplate) {
        super();
        this.env = env;
        this.helper = helper;
        this.restTemplate = restTemplate;
    }

    @Override
    protected BaseResponseBean<List<AnyCalcOrGetResponseBean>> createResponse() {
        return null;
    }

    @Override
    protected boolean validate(BaseRequestBean<List<AnyCalcRequestBean>> request,
                               BaseResponseBean<List<AnyCalcOrGetResponseBean>> response) {
        return true;
    }

    @Override
    protected List<AnyDataModel> createIntermediateObject(BaseRequestBean<List<AnyCalcRequestBean>> request) {

        String url = env.getProperty("mockanyrestapi.url.getany");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>>> mockAnyRestApiResponse = restTemplate.exchange(
                Objects.requireNonNull(url),
                HttpMethod.POST,
                new HttpEntity<>(request, headers),
                new ParameterizedTypeReference<>() {
                });

        MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>> mockAnyRestApiResponseBody = mockAnyRestApiResponse.getBody();
        List<MockAnyRestApiGetResponseBean> payload = Objects.requireNonNull(mockAnyRestApiResponseBody).getPayload();
        List<AnyDataModel> anyDataModelList = new ArrayList<>();
        AnyDataModel anyDataModel = new AnyDataModel();
        MockAnyRestApiGetResponseBean mockAnyRestApiResponseBean = payload.get(0);
        anyDataModel.id = mockAnyRestApiResponseBean.getId();
        anyDataModel.name = mockAnyRestApiResponseBean.getName();
        anyDataModel.type = mockAnyRestApiResponseBean.getType();
        anyDataModelList.add(anyDataModel);

        return anyDataModelList;
    }

    @Override
    protected void execute(BaseRequestBean<List<AnyCalcRequestBean>> request,
                           BaseResponseBean<List<AnyCalcOrGetResponseBean>> response,
                           List<AnyDataModel> anyDataModels) {

    }
}
