package com.example.anyrestapi.service.impl.helper;

import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.request.payload.MockAnyRestApiGetRequestBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseBean;
import com.example.anyrestapicore.bean.mockanyrestapi.response.payload.MockAnyRestApiGetResponseBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnyGetServiceHelper {

    public ResponseEntity<MockAnyRestApiBaseResponseBean<List<MockAnyRestApiGetResponseBean>>> getAnyFromMockAnyRestApi(RestTemplate restTemplate,
                                                                                                                        String url,
                                                                                                                        List<AnyDataModel> anyDataModelList) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MockAnyRestApiBaseRequestBean<List<MockAnyRestApiGetRequestBean>> mockAnyRestApiRequest = new MockAnyRestApiBaseRequestBean<>();
        mockAnyRestApiRequest.setUserName("USERNAME-000-0000");
        mockAnyRestApiRequest.setAuthKey("AUTHKEY-000-0000");

        List<MockAnyRestApiGetRequestBean> mockAnyRestApiRequestPayload = new ArrayList<>();

        for (AnyDataModel anyDataModel : anyDataModelList) {
            MockAnyRestApiGetRequestBean mockAnyRestApiRequestData = new MockAnyRestApiGetRequestBean();
            mockAnyRestApiRequestData.setId(anyDataModel.id);
            mockAnyRestApiRequestPayload.add(mockAnyRestApiRequestData);
        }

        mockAnyRestApiRequest.setPayload(mockAnyRestApiRequestPayload);

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(mockAnyRestApiRequest, headers),
                new ParameterizedTypeReference<>() {
                });
    }
}
