package com.example.anyrestapi.service.impl.mock;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.request.payload.AnyUpdateRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.bean.response.payload.AnyCalcOrGetResponseBean;
import com.example.anyrestapicore.bean.response.payload.AnyUpdateResponseBean;
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
public class MockAnyUpdateService extends BaseService<
        BaseRequestBean<List<AnyUpdateRequestBean>>,
        BaseResponseBean<AnyUpdateResponseBean>> {

    private static final Logger logger = LoggerFactory.getLogger(MockAnyUpdateService.class);
    private final Environment env;

    public MockAnyUpdateService(Environment env) {
        super();
        this.env = env;
    }

    @Override
    protected BaseResponseBean<AnyUpdateResponseBean> createResponse() {
        return null;
    }

    @Override
    protected boolean validate(BaseRequestBean<List<AnyUpdateRequestBean>> request,
                               BaseResponseBean<AnyUpdateResponseBean> response) {
        return true;
    }

    @Override
    protected List<AnyDataModel> createIntermediateObject(BaseRequestBean<List<AnyUpdateRequestBean>> request) {
        return null;
    }

    @Override
    protected void execute(BaseRequestBean<List<AnyUpdateRequestBean>> request,
                           BaseResponseBean<AnyUpdateResponseBean> response,
                           List<AnyDataModel> anyDataModels) {
        try {
            BaseResponseBean<List<AnyCalcOrGetResponseBean>> mockResponse = new ObjectMapper().readValue(
                    new ClassPathResource("mockUpdateAny-response.json").getFile(),
                    new TypeReference<>() {
                    });

            BeanUtils.copyProperties(mockResponse, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
