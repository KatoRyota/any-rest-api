package com.example.anyrestapi.service.impl.mock;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.bean.response.payload.AnyCalcOrGetResponseBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
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
public class MockAnyGetService extends BaseService<
        BaseRequestBean<List<String>>,
        BaseResponseBean<List<AnyCalcOrGetResponseBean>>> {

    private static final Logger logger = LoggerFactory.getLogger(MockAnyGetService.class);
    private final Environment env;

    public MockAnyGetService(
            Environment env) {

        super();
        this.env = env;
    }

    @Override
    protected BaseResponseBean<List<AnyCalcOrGetResponseBean>> createResponse() {
        return new BaseResponseBean<>();
    }

    @Override
    protected boolean validate(
            BaseRequestBean<List<String>> request,
            BaseResponseBean<List<AnyCalcOrGetResponseBean>> response) {

        return true;
    }

    @Override
    protected List<AnyDataModel> createIntermediateObject(
            BaseRequestBean<List<String>> request) {

        return null;
    }

    @Override
    protected void execute(
            BaseRequestBean<List<String>> request,
            BaseResponseBean<List<AnyCalcOrGetResponseBean>> response,
            List<AnyDataModel> anyDataModels) {

        try {
            BaseResponseBean<List<AnyCalcOrGetResponseBean>> mockResponse =
                    new ObjectMapper().readValue(
                            new ClassPathResource("mockGetAny-response.json").getFile(),
                            new TypeReference<>() {
                            });

            BeanUtils.copyProperties(mockResponse, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
