package com.example.anyrestapi.service.impl;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapi.service.impl.helper.AnyGetServiceHelper;
import com.example.anyrestapicore.model.common.AnyDataModel;
import com.example.anyrestapicore.model.request.BaseRequestModel;
import com.example.anyrestapicore.model.request.payload.AnyGetRequestModel;
import com.example.anyrestapicore.model.response.BaseResponseModel;
import com.example.anyrestapicore.model.response.payload.AnyGetResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class AnyGetService extends BaseService<
        BaseRequestModel<AnyGetRequestModel>,
        BaseResponseModel<AnyGetResponseModel>> {

    private static final Logger logger = LoggerFactory.getLogger(AnyGetService.class);
    private final Environment env;
    private final AnyGetServiceHelper helper;

    public AnyGetService(Environment env, AnyGetServiceHelper helper) {
        super();
        this.env = env;
        this.helper = helper;
    }

    @Override
    protected BaseResponseModel<AnyGetResponseModel> createResponseModel() {
        return null;
    }

    @Override
    protected boolean validate(BaseRequestModel<AnyGetRequestModel> request,
                               BaseResponseModel<AnyGetResponseModel> response) {
        return false;
    }

    @Override
    protected AnyDataModel createIntermediateObject(BaseRequestModel<AnyGetRequestModel> request) {
        return null;
    }

    @Override
    protected void execute(BaseRequestModel<AnyGetRequestModel> request,
                           BaseResponseModel<AnyGetResponseModel> response,
                           AnyDataModel anyDataModel) {

    }
}
