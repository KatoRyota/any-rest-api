package com.example.anyrestapi.service.impl;

import com.example.anyrestapi.service.BaseService;
import com.example.anyrestapi.service.impl.helper.AnyGetServiceHelper;
import com.example.anyrestapicore.bean.database.AnyArtifactRecordBean;
import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiGetRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiGetResponseBean;
import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.bean.response.errors.ErrorBean;
import com.example.anyrestapicore.bean.response.payload.AnyCalcOrGetResponseBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
import com.example.anyrestapicore.model.common.partial.AnyPartialDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RestTemplate restTemplate;

    public AnyGetService(Environment env,
                         AnyGetServiceHelper helper,
                         NamedParameterJdbcTemplate jdbcTemplate,
                         RestTemplate restTemplate) {
        super();
        this.env = env;
        this.helper = helper;
        this.jdbcTemplate = jdbcTemplate;
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

        String sql = "" +
                "select id, name, type " +
                "from any_artifact " +
                "where id in (:idList)";

        List<AnyArtifactRecordBean> anyArtifactRecordList = jdbcTemplate.query(
                sql,
                new MapSqlParameterSource()
                        .addValue(
                                "idList", String.join(",", request.getPayload())),
                new BeanPropertyRowMapper<>(AnyArtifactRecordBean.class));

        List<AnyDataModel> anyDataModelList = new ArrayList<>();

        for (AnyArtifactRecordBean anyArtifactRecord : anyArtifactRecordList) {
            AnyDataModel anyDataModel = new AnyDataModel();
            anyDataModel.id = anyArtifactRecord.getId();
            anyDataModel.type = anyArtifactRecord.getType();
            anyDataModel.name = anyArtifactRecord.getName();
            anyDataModelList.add(anyDataModel);
        }

        String url = Objects.requireNonNull(env.getProperty("mockanyrestapi.url.getany"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MockAnyRestApiBaseRequestBean<List<MockAnyRestApiGetRequestBean>> mockAnyRestApiRequest = new MockAnyRestApiBaseRequestBean<>();
        mockAnyRestApiRequest.setUserName("userName-000-0000");
        mockAnyRestApiRequest.setAuthKey("authKey-000-0000");

        List<MockAnyRestApiGetRequestBean> mockAnyRestApiRequestPayload = new ArrayList<>();

        for (AnyDataModel anyDataModel : anyDataModelList) {
            MockAnyRestApiGetRequestBean mockAnyRestApiRequestData = new MockAnyRestApiGetRequestBean();
            mockAnyRestApiRequestData.setId(anyDataModel.id);
            mockAnyRestApiRequestPayload.add(mockAnyRestApiRequestData);
        }

        mockAnyRestApiRequest.setPayload(mockAnyRestApiRequestPayload);

        ResponseEntity<MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>>> mockAnyRestApiResponse = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(mockAnyRestApiRequest, headers),
                new ParameterizedTypeReference<>() {
                });

        MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>> mockAnyRestApiResponseBody = Objects.requireNonNull(mockAnyRestApiResponse.getBody());
        List<MockAnyRestApiGetResponseBean> mockAnyRestApiResponsePayload = mockAnyRestApiResponseBody.getPayload();

        for (AnyDataModel anyDataModel : anyDataModelList) {
            List<AnyPartialDataModel> anyPartialDataModelList = new ArrayList<>();

            for (MockAnyRestApiGetResponseBean mockAnyRestApiResponseData : mockAnyRestApiResponsePayload) {
                if (anyDataModel.id.equals(mockAnyRestApiResponseData.getId())) {
                    AnyPartialDataModel anyPartialDataModel = new AnyPartialDataModel();
                    anyPartialDataModel.partialId = mockAnyRestApiResponseData.getId();
                    anyPartialDataModel.partialName = mockAnyRestApiResponseData.getName();
                    anyPartialDataModel.partialType = mockAnyRestApiResponseData.getType();
                    anyPartialDataModelList.add(anyPartialDataModel);
                }
            }

            anyDataModel.anyPartialDataModels = anyPartialDataModelList;
        }

        return anyDataModelList;
    }

    @Override
    protected void execute(BaseRequestBean<List<String>> request,
                           BaseResponseBean<List<AnyCalcOrGetResponseBean>> response,
                           List<AnyDataModel> anyDataModels) {

        response.setStatusCode("statusCode-000-0000");
        response.setMessage("message-000-0000");

        List<AnyCalcOrGetResponseBean> anyCalcOrGetResponseList = new ArrayList<>();

        for (AnyDataModel anyDataModel : anyDataModels) {
            AnyCalcOrGetResponseBean anyCalcOrGetResponse = new AnyCalcOrGetResponseBean();
            anyCalcOrGetResponse.setId(anyDataModel.id);
            anyCalcOrGetResponse.setName(anyDataModel.name);
            anyCalcOrGetResponse.setType(anyDataModel.type);

            for (AnyPartialDataModel anyPartialDataModel : anyDataModel.anyPartialDataModels) {
                anyCalcOrGetResponse.setPartialId(anyPartialDataModel.partialId);
                anyCalcOrGetResponse.setPartialName(anyPartialDataModel.partialName);
                anyCalcOrGetResponse.setPartialType(anyPartialDataModel.partialType);
            }

            anyCalcOrGetResponseList.add(anyCalcOrGetResponse);
        }

        response.setPayload(anyCalcOrGetResponseList);

        List<ErrorBean> errors = new ArrayList<>();
        ErrorBean error = new ErrorBean();
        error.setBindId("bindId-000-0000");
        error.setCode("code-000-0000");
        error.setMessage("message-000-0000");
        errors.add(error);
        response.setErrors(errors);
    }
}
