package com.example.mockanyrestapi.service.impl;

import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestModel;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiGetRequestModel;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseModel;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiGetResponseModel;
import com.example.mockanyrestapi.service.BaseMockAnyRestApiService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class MockAnyRestApiGetService extends BaseMockAnyRestApiService<
        MockAnyRestApiBaseRequestModel<MockAnyRestApiGetRequestModel>,
        MockAnyRestApiBaseResponseModel<MockAnyRestApiGetResponseModel>> {

    private static final Logger logger = LoggerFactory.getLogger(MockAnyRestApiGetService.class);
    private final Environment env;

    public MockAnyRestApiGetService(Environment env) {
        super();
        this.env = env;
    }

    @Override
    protected MockAnyRestApiBaseResponseModel<MockAnyRestApiGetResponseModel> createResponseModel() {
        return new MockAnyRestApiBaseResponseModel<>();
    }

    @Override
    protected boolean validate(MockAnyRestApiBaseRequestModel<MockAnyRestApiGetRequestModel> request,
                               MockAnyRestApiBaseResponseModel<MockAnyRestApiGetResponseModel> response) {
        return true;
    }

    @Override
    protected void execute(MockAnyRestApiBaseRequestModel<MockAnyRestApiGetRequestModel> request,
                           MockAnyRestApiBaseResponseModel<MockAnyRestApiGetResponseModel> response) {
        try {
            MockAnyRestApiBaseResponseModel<MockAnyRestApiGetResponseModel> mockResponse = new ObjectMapper().readValue(
                    new ClassPathResource("mock-response.json").getFile(),
                    new TypeReference<MockAnyRestApiBaseResponseModel<MockAnyRestApiGetResponseModel>>() {
                    });

            BeanUtils.copyProperties(mockResponse, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
