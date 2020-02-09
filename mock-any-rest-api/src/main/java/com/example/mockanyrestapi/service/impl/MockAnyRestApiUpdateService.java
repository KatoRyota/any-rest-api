package com.example.mockanyrestapi.service.impl;

import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiUpdateRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiUpdateResponseBean;
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
public class MockAnyRestApiUpdateService extends BaseMockAnyRestApiService<
        MockAnyRestApiBaseRequestBean<List<MockAnyRestApiUpdateRequestBean>>,
        MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean>> {

    private static final Logger logger = LoggerFactory.getLogger(MockAnyRestApiUpdateService.class);
    private final Environment env;

    public MockAnyRestApiUpdateService(Environment env) {
        super();
        this.env = env;
    }

    @Override
    protected MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean> createResponseModel() {
        return new MockAnyRestApiBaseResponseBean<>();
    }

    @Override
    protected boolean validate(MockAnyRestApiBaseRequestBean<List<MockAnyRestApiUpdateRequestBean>> request,
                               MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean> response) {
        return true;
    }

    @Override
    protected void execute(MockAnyRestApiBaseRequestBean<List<MockAnyRestApiUpdateRequestBean>> request,
                           MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean> response) {
        try {
            MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean> mockResponse = new ObjectMapper().readValue(
                    new ClassPathResource("updateAny-response.json").getFile(),
                    new TypeReference<>() {
                    });

            BeanUtils.copyProperties(mockResponse, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
