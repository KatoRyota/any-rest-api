package com.example.mockanyrestapi.service.impl;

import com.example.anyrestapicore.model.mockanyrestapi.request.BaseMockAnyRestApiRequestModel;
import com.example.anyrestapicore.model.mockanyrestapi.request.payload.MockAnyRestApiGetRequestModel;
import com.example.anyrestapicore.model.mockanyrestapi.response.BaseMockAnyRestApiResponseModel;
import com.example.anyrestapicore.model.mockanyrestapi.response.payload.MockAnyRestApiGetResponseModel;
import com.example.mockanyrestapi.service.BaseMockAnyRestApiService;
import com.example.mockanyrestapi.service.impl.helper.MockAnyRestApiGetServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MockAnyRestApiGetService extends BaseMockAnyRestApiService<
        BaseMockAnyRestApiRequestModel<MockAnyRestApiGetRequestModel>,
        BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel>> {

    private static final Logger logger = LoggerFactory.getLogger(MockAnyRestApiGetService.class);
    private final Environment env;
    private final MockAnyRestApiGetServiceHelper helper;

    public MockAnyRestApiGetService(Environment env, MockAnyRestApiGetServiceHelper helper) {
        super();
        this.env = env;
        this.helper = helper;
    }

    @Override
    protected BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel> createResponseModel() {
        return null;
    }

    @Override
    protected boolean validate(BaseMockAnyRestApiRequestModel<MockAnyRestApiGetRequestModel> request,
                               BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel> response) {
        return false;
    }

    @Override
    protected void execute(BaseMockAnyRestApiRequestModel<MockAnyRestApiGetRequestModel> request,
                           BaseMockAnyRestApiResponseModel<MockAnyRestApiGetResponseModel> response) {

    }
}
