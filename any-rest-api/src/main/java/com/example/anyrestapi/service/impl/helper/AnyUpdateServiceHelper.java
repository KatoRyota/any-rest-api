package com.example.anyrestapi.service.impl.helper;

import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiUpdateRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiUpdateResponseBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AnyUpdateServiceHelper {

    private final Environment env;
    private final RestTemplate restTemplate;

    public AnyUpdateServiceHelper(
            Environment env,
            RestTemplate restTemplate) {

        this.env = env;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<MockAnyRestApiBaseResponseBean<MockAnyRestApiUpdateResponseBean>> updateAnyOfMockAnyRestApi(
            List<AnyDataModel> anyDataModelList) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MockAnyRestApiBaseRequestBean<List<MockAnyRestApiUpdateRequestBean>> mockAnyRestApiRequest = new MockAnyRestApiBaseRequestBean<>();
        mockAnyRestApiRequest.setUserName("USERNAME-000-0000");
        mockAnyRestApiRequest.setAuthKey("AUTHKEY-000-0000");

        List<MockAnyRestApiUpdateRequestBean> mockAnyRestApiRequestPayload = new ArrayList<>();

        for (AnyDataModel anyDataModel : anyDataModelList) {
            MockAnyRestApiUpdateRequestBean mockAnyRestApiRequestData = new MockAnyRestApiUpdateRequestBean();
            mockAnyRestApiRequestData.setId(anyDataModel.id);
            mockAnyRestApiRequestData.setName(anyDataModel.name);
            mockAnyRestApiRequestData.setType(anyDataModel.type);
            mockAnyRestApiRequestPayload.add(mockAnyRestApiRequestData);
        }

        mockAnyRestApiRequest.setPayload(mockAnyRestApiRequestPayload);

        return restTemplate.exchange(
                Objects.requireNonNull(
                        env.getProperty("mockanyrestapi.url.updateany")),
                HttpMethod.POST,
                new HttpEntity<>(mockAnyRestApiRequest, headers),
                new ParameterizedTypeReference<>() {
                });
    }
}
