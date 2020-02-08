package com.example.anyrestapi.service.impl.mock;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.request.payload.AnyCalcRequestBean;
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
public class MockAnyCalcService extends BaseService<
        BaseRequestBean<List<AnyCalcRequestBean>>,
        BaseResponseBean<List<AnyCalcOrGetResponseBean>>> {

    private static final Logger logger = LoggerFactory.getLogger(MockAnyCalcService.class);
    private final Environment env;

    public MockAnyCalcService(Environment env) {
        super();
        this.env = env;
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
        return null;
    }

    @Override
    protected void execute(BaseRequestBean<List<AnyCalcRequestBean>> request,
                           BaseResponseBean<List<AnyCalcOrGetResponseBean>> response,
                           List<AnyDataModel> anyDataModel) {
        try {
            BaseResponseBean<List<AnyCalcOrGetResponseBean>> mockResponse = new ObjectMapper().readValue(
                    new ClassPathResource("mockCalcAny-response.json").getFile(),
                    new TypeReference<>() {
                    });

            BeanUtils.copyProperties(mockResponse, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
