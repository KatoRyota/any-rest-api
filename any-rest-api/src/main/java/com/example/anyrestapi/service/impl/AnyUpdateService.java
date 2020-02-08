package com.example.anyrestapi.service.impl;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapi.service.impl.helper.AnyUpdateServiceHelper;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.request.payload.AnyUpdateRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.bean.response.payload.AnyUpdateResponseBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnyUpdateService extends BaseService<
        BaseRequestBean<List<AnyUpdateRequestBean>>,
        BaseResponseBean<AnyUpdateResponseBean>> {

    private static final Logger logger = LoggerFactory.getLogger(AnyUpdateService.class);
    private final Environment env;
    private final AnyUpdateServiceHelper helper;

    public AnyUpdateService(Environment env, AnyUpdateServiceHelper helper) {
        super();
        this.env = env;
        this.helper = helper;
    }

    @Override
    protected BaseResponseBean<AnyUpdateResponseBean> createResponse() {
        return null;
    }

    @Override
    protected boolean validate(BaseRequestBean<List<AnyUpdateRequestBean>> request,
                               BaseResponseBean<AnyUpdateResponseBean> response) {
        return false;
    }

    @Override
    protected List<AnyDataModel> createIntermediateObject(BaseRequestBean<List<AnyUpdateRequestBean>> request) {
        return null;
    }

    @Override
    protected void execute(BaseRequestBean<List<AnyUpdateRequestBean>> request,
                           BaseResponseBean<AnyUpdateResponseBean> response,
                           List<AnyDataModel> anyDataModels) {

    }
}
