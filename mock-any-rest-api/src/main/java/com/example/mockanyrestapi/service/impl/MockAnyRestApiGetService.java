package com.example.mockanyrestapi.service.impl;

import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiGetRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiGetResponseBean;
import com.example.mockanyrestapi.service.BaseMockAnyRestApiService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockAnyRestApiGetService extends BaseMockAnyRestApiService<
        MockAnyRestApiBaseRequestBean<List<MockAnyRestApiGetRequestBean>>,
        MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>>> {

    private static final Logger logger = LoggerFactory.getLogger(MockAnyRestApiGetService.class);
    private final Environment env;

    public MockAnyRestApiGetService(
            Environment env) {

        super();
        this.env = env;
    }

    @Override
    protected MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>> createResponseModel() {
        return new MockAnyRestApiBaseResponseBean<>();
    }

    @Override
    protected boolean validate(
            MockAnyRestApiBaseRequestBean<List<MockAnyRestApiGetRequestBean>> request,
            MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>> response) {

        return true;
    }

    @Override
    protected void execute(
            MockAnyRestApiBaseRequestBean<List<MockAnyRestApiGetRequestBean>> request,
            MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>> response) {

        try {
            MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>> mockResponse =
                    new ObjectMapper().readValue(
                            new ClassPathResource("getAny-response.json").getFile(),
                            new TypeReference<>() {
                            });

            BeanUtils.copyProperties(mockResponse, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
