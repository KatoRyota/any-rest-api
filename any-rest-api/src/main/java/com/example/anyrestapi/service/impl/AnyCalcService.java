package com.example.anyrestapi.service.impl;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapi.service.impl.helper.AnyGetServiceHelper;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.request.payload.AnyCalcRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.bean.response.payload.AnyCalcOrGetResponseBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnyCalcService extends BaseService<
        BaseRequestBean<List<AnyCalcRequestBean>>,
        BaseResponseBean<List<AnyCalcOrGetResponseBean>>> {

    private static final Logger logger = LoggerFactory.getLogger(AnyCalcService.class);
    private final Environment env;
    private final AnyGetServiceHelper helper;

    public AnyCalcService(Environment env, AnyGetServiceHelper helper) {
        super();
        this.env = env;
        this.helper = helper;
    }

    @Override
    protected BaseResponseBean<List<AnyCalcOrGetResponseBean>> createResponse() {
        return null;
    }

    @Override
    protected boolean validate(BaseRequestBean<List<AnyCalcRequestBean>> request,
                               BaseResponseBean<List<AnyCalcOrGetResponseBean>> response) {
        return false;
    }

    @Override
    protected List<AnyDataModel> createIntermediateObject(BaseRequestBean<List<AnyCalcRequestBean>> request) {
        return null;
    }

    @Override
    protected void execute(BaseRequestBean<List<AnyCalcRequestBean>> request,
                           BaseResponseBean<List<AnyCalcOrGetResponseBean>> response,
                           List<AnyDataModel> anyDataModels) {

    }
}
