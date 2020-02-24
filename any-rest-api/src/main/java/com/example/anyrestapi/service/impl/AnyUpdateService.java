package com.example.anyrestapi.service.impl;

import com.example.anyrestapi.repository.AnyArtifactRepository;
import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapi.service.impl.helper.AnyUpdateServiceHelper;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiUpdateResponseBean;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.request.payload.AnyUpdateRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.bean.response.errors.ErrorBean;
import com.example.anyrestapicore.bean.response.payload.AnyUpdateResponseBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
import com.example.anyrestapicore.model.common.partial.AnyPartialDataModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AnyUpdateService extends BaseService<
        BaseRequestBean<List<AnyUpdateRequestBean>>,
        BaseResponseBean<AnyUpdateResponseBean>> {

    private static final Logger logger = LoggerFactory.getLogger(AnyUpdateService.class);
    private final AnyUpdateServiceHelper helper;
    private final AnyArtifactRepository anyArtifactRepository;

    public AnyUpdateService(
            AnyUpdateServiceHelper helper,
            AnyArtifactRepository anyArtifactRepository) {

        super();
        this.helper = helper;
        this.anyArtifactRepository = anyArtifactRepository;
    }

    @Override
    protected BaseResponseBean<AnyUpdateResponseBean> createResponse() {
        return new BaseResponseBean<>();
    }

    @Override
    protected boolean validate(
            BaseRequestBean<List<AnyUpdateRequestBean>> request,
            BaseResponseBean<AnyUpdateResponseBean> response) {

        return true;
    }

    @Override
    protected List<AnyDataModel> createIntermediateObject(
            BaseRequestBean<List<AnyUpdateRequestBean>> request) {

        List<AnyDataModel> anyDataModelList = new ArrayList<>();

        for (AnyUpdateRequestBean anyUpdateRequest : request.getPayload()) {
            AnyDataModel anyDataModel = new AnyDataModel();
            anyDataModel.id = anyUpdateRequest.getId();
            anyDataModel.name = anyUpdateRequest.getName();
            anyDataModel.type = anyUpdateRequest.getType();

            List<AnyPartialDataModel> anyPartialDataModelList = new ArrayList<>();
            AnyPartialDataModel anyPartialDataModel = new AnyPartialDataModel();
            anyPartialDataModel.id = anyUpdateRequest.getId();
            anyPartialDataModel.name = anyUpdateRequest.getName();
            anyPartialDataModel.type = anyUpdateRequest.getType();
            anyPartialDataModelList.add(anyPartialDataModel);

            anyDataModel.anyPartialDataModels = anyPartialDataModelList;
            anyDataModelList.add(anyDataModel);
        }

        return anyDataModelList;
    }

    @Override
    protected void execute(
            BaseRequestBean<List<AnyUpdateRequestBean>> request,
            BaseResponseBean<AnyUpdateResponseBean> response,
            List<AnyDataModel> anyDataModels) {

        try {
            int[] updateCounts = anyArtifactRepository.update(anyDataModels);

            if (logger.isDebugEnabled()) {
                logger.debug("DB Update Counts->[{}]",
                        new ObjectMapper().writeValueAsString(updateCounts));
            }

            ResponseEntity<MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean>> mockAnyRestApiResponse =
                    helper.updateAnyOfMockAnyRestApi(
                            anyDataModels);

            MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean> mockAnyRestApiResponseBody =
                    Objects.requireNonNull(
                            mockAnyRestApiResponse.getBody());

            if (logger.isDebugEnabled()) {
                logger.debug("mock-any-rest-api Response->[{}]",
                        new ObjectMapper().writeValueAsString(
                                mockAnyRestApiResponseBody));
            }

            MockAnyRestApiUpdateResponseBean mockAnyRestApiResponsePayload =
                    Objects.requireNonNull(
                            mockAnyRestApiResponseBody.getPayload());

            response.setStatusCode("STATUS-000-0000");
            response.setMessage("正常終了");

            AnyUpdateResponseBean anyUpdateResponse = new AnyUpdateResponseBean();
            anyUpdateResponse.setUpdateCount(
                    mockAnyRestApiResponsePayload.getUpdateCount());
            response.setPayload(anyUpdateResponse);

            List<ErrorBean> errors = new ArrayList<>();
            ErrorBean error = new ErrorBean();
            error.setCode("ERROR-000-0000");
            error.setBindId("ID-000-0000");
            error.setMessage("エラーが発生しました。");
            errors.add(error);
            response.setErrors(errors);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
