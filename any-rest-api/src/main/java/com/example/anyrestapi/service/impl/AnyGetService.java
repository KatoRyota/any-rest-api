package com.example.anyrestapi.service.impl;

import com.example.anyrestapi.repository.AnyArtifactRepository;
import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapi.service.impl.helper.AnyGetServiceHelper;
import com.example.anyrestapicore.bean.database.AnyArtifactRecordBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiGetResponseBean;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.bean.response.errors.ErrorBean;
import com.example.anyrestapicore.bean.response.payload.AnyCalcOrGetResponseBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
import com.example.anyrestapicore.model.common.partial.AnyPartialDataModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AnyGetService extends BaseService<
        BaseRequestBean<List<String>>,
        BaseResponseBean<List<AnyCalcOrGetResponseBean>>> {

    private static final Logger logger = LoggerFactory.getLogger(AnyGetService.class);
    private final Environment env;
    private final AnyGetServiceHelper helper;
    private final AnyArtifactRepository anyArtifactRepository;
    private final RestTemplate restTemplate;

    public AnyGetService(Environment env,
                         AnyGetServiceHelper helper,
                         AnyArtifactRepository anyArtifactRepository,
                         RestTemplate restTemplate) {
        super();
        this.env = env;
        this.helper = helper;
        this.anyArtifactRepository = anyArtifactRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    protected BaseResponseBean<List<AnyCalcOrGetResponseBean>> createResponse() {
        return new BaseResponseBean<>();
    }

    @Override
    protected boolean validate(BaseRequestBean<List<String>> request,
                               BaseResponseBean<List<AnyCalcOrGetResponseBean>> response) {
        return true;
    }

    @Override
    protected List<AnyDataModel> createIntermediateObject(BaseRequestBean<List<String>> request) {

        List<AnyDataModel> anyDataModelList = new ArrayList<>();

        try {
            List<AnyArtifactRecordBean> anyArtifactRecordList = anyArtifactRepository.select(request.getPayload());

            if (logger.isDebugEnabled()) {
                logger.debug("DB Result Set->[{}]", new ObjectMapper().writeValueAsString(anyArtifactRecordList));
            }

            for (AnyArtifactRecordBean anyArtifactRecord : anyArtifactRecordList) {
                AnyDataModel anyDataModel = new AnyDataModel();
                anyDataModel.id = anyArtifactRecord.getId();
                anyDataModel.type = anyArtifactRecord.getType();
                anyDataModel.name = anyArtifactRecord.getName();
                anyDataModelList.add(anyDataModel);
            }

            String url = Objects.requireNonNull(env.getProperty("mockanyrestapi.url.getany"));
            ResponseEntity<MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>>> mockAnyRestApiResponse = helper.getAnyFromMockAnyRestApi(restTemplate, url, anyDataModelList);
            MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>> mockAnyRestApiResponseBody = Objects.requireNonNull(mockAnyRestApiResponse.getBody());
            List<MockAnyRestApiGetResponseBean> mockAnyRestApiResponsePayload = mockAnyRestApiResponseBody.getPayload();

            if (logger.isDebugEnabled()) {
                logger.debug("mock-any-rest-api Response->[{}]", new ObjectMapper().writeValueAsString(mockAnyRestApiResponseBody));
            }

            for (AnyDataModel anyDataModel : anyDataModelList) {
                List<AnyPartialDataModel> anyPartialDataModelList = new ArrayList<>();

                for (MockAnyRestApiGetResponseBean mockAnyRestApiResponseData : mockAnyRestApiResponsePayload) {
                    if (anyDataModel.id.equals(mockAnyRestApiResponseData.getId())) {
                        AnyPartialDataModel anyPartialDataModel = new AnyPartialDataModel();
                        anyPartialDataModel.id = mockAnyRestApiResponseData.getId();
                        anyPartialDataModel.name = mockAnyRestApiResponseData.getName();
                        anyPartialDataModel.type = mockAnyRestApiResponseData.getType();
                        anyPartialDataModelList.add(anyPartialDataModel);
                    }
                }

                anyDataModel.anyPartialDataModels = anyPartialDataModelList;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return anyDataModelList;
    }

    @Override
    protected void execute(BaseRequestBean<List<String>> request,
                           BaseResponseBean<List<AnyCalcOrGetResponseBean>> response,
                           List<AnyDataModel> anyDataModels) {

        response.setStatusCode("STATUS-000-0000");
        response.setMessage("正常終了");

        List<AnyCalcOrGetResponseBean> anyCalcOrGetResponseList = new ArrayList<>();

        for (AnyDataModel anyDataModel : anyDataModels) {
            AnyCalcOrGetResponseBean anyCalcOrGetResponse = new AnyCalcOrGetResponseBean();
            anyCalcOrGetResponse.setId(anyDataModel.id);
            anyCalcOrGetResponse.setName(anyDataModel.name);
            anyCalcOrGetResponse.setType(anyDataModel.type);

            for (AnyPartialDataModel anyPartialDataModel : anyDataModel.anyPartialDataModels) {
                anyCalcOrGetResponse.setPartialId(anyPartialDataModel.id);
                anyCalcOrGetResponse.setPartialName(anyPartialDataModel.name);
                anyCalcOrGetResponse.setPartialType(anyPartialDataModel.type);
            }

            anyCalcOrGetResponseList.add(anyCalcOrGetResponse);
        }

        response.setPayload(anyCalcOrGetResponseList);

        List<ErrorBean> errors = new ArrayList<>();
        ErrorBean error = new ErrorBean();
        error.setCode("ERROR-000-0000");
        error.setBindId("ID-000-0000");
        error.setMessage("エラーが発生しました。");
        errors.add(error);
        response.setErrors(errors);
    }
}
