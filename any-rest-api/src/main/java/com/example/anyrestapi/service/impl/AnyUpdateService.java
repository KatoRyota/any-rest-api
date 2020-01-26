package com.example.anyrestapi.service.impl;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapi.service.impl.helper.AnyUpdateServiceHelper;
import com.example.anyrestapicore.model.common.AnyDataModel;
import com.example.anyrestapicore.model.request.BaseRequestModel;
import com.example.anyrestapicore.model.request.payload.AnyUpdateRequestModel;
import com.example.anyrestapicore.model.response.BaseResponseModel;
import com.example.anyrestapicore.model.response.payload.AnyUpdateResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class AnyUpdateService extends BaseService<
        BaseRequestModel<AnyUpdateRequestModel>,
        BaseResponseModel<AnyUpdateResponseModel>> {

    private static final Logger logger = LoggerFactory.getLogger(AnyUpdateService.class);
    private final Environment env;
    private final AnyUpdateServiceHelper helper;

    public AnyUpdateService(Environment env, AnyUpdateServiceHelper helper) {
        super();
        this.env = env;
        this.helper = helper;
    }

    @Override
    protected BaseResponseModel<AnyUpdateResponseModel> createResponseModel() {
        return null;
    }

    @Override
    protected boolean validate(BaseRequestModel<AnyUpdateRequestModel> request,
                               BaseResponseModel<AnyUpdateResponseModel> response) {
        return false;
    }

    @Override
    protected AnyDataModel createIntermediateObject(BaseRequestModel<AnyUpdateRequestModel> request) {
        return null;
    }

    @Override
    protected void execute(BaseRequestModel<AnyUpdateRequestModel> request,
                           BaseResponseModel<AnyUpdateResponseModel> response,
                           AnyDataModel anyDataModel) {

    }
}
