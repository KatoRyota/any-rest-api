package com.example.mockanyrestapi.service.impl;

import com.example.anyrestapicore.model.mockanyrestapi.request.BaseMockAnyRestApiRequestModel;
import com.example.anyrestapicore.model.mockanyrestapi.request.payload.MockAnyRestApiGetRequestModel;
import com.example.anyrestapicore.model.mockanyrestapi.response.BaseMockAnyRestApiResponseModel;
import com.example.anyrestapicore.model.mockanyrestapi.response.payload.MockAnyRestApiGetResponseModel;
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
        BaseMockAnyRestApiRequestModel<MockAnyRestApiGetRequestModel>,
        BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel>> {

    private static final Logger logger = LoggerFactory.getLogger(MockAnyRestApiGetService.class);
    private final Environment env;

    public MockAnyRestApiGetService(Environment env) {
        super();
        this.env = env;
    }

    @Override
    protected BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel> createResponseModel() {
        return new BaseMockAnyRestApiResponseModel<>();
    }

    @Override
    protected boolean validate(BaseMockAnyRestApiRequestModel<MockAnyRestApiGetRequestModel> request,
                               BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel> response) {
        return true;
    }

    @Override
    protected void execute(BaseMockAnyRestApiRequestModel<MockAnyRestApiGetRequestModel> request,
                           BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel> response) {
        try {
            BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel> mockResponse = new ObjectMapper().readValue(
                    new ClassPathResource("mock-response.json").getFile(),
                    new TypeReference<BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel>>() {
                    });

            BeanUtils.copyProperties(mockResponse, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
