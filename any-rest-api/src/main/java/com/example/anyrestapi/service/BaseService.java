package com.example.anyrestapi.service;

import com.example.anyrestapicore.bean.request.BaseRequestBean;
import com.example.anyrestapicore.bean.response.BaseResponseBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public abstract class BaseService<
        T1 extends BaseRequestBean<?>,
        T2 extends BaseResponseBean<?>> {

    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
    private static final Logger restLogger = LoggerFactory.getLogger("rest");
    private static final Logger performanceLogger = LoggerFactory.getLogger("performance");

    public T2 process(T1 request) {
        try {
            logger.info("[start] {}", this.getClass().getCanonicalName());
            LocalTime startTime = LocalTime.now();
            restLogger.info("request->[{}]", new ObjectMapper().writeValueAsString(request));

            T2 response = createResponse();

            if (!validate(request, response)) {
                logger.info("Request parameter is invalid.:request->[{}],response->[{}]",
                        new ObjectMapper().writeValueAsString(request),
                        new ObjectMapper().writeValueAsString(response));

                logger.info("[end] {}", this.getClass().getCanonicalName());

                return response;
            }

            List<AnyDataModel> anyDataModels = createIntermediateObject(request);
            logger.info("anyDataModels->[{}]", new ObjectMapper().writeValueAsString(anyDataModels));

            execute(request, response, anyDataModels);

            restLogger.info("response->[{}]", new ObjectMapper().writeValueAsString(response));
            LocalTime endTime = LocalTime.now();
            long processTime = ChronoUnit.SECONDS.between(startTime, endTime);
            performanceLogger.info("processTime->[{}]", processTime);
            logger.info("[end] {}", this.getClass().getCanonicalName());

            return response;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract T2 createResponse();

    protected abstract boolean validate(T1 request, T2 response);

    protected abstract List<AnyDataModel> createIntermediateObject(T1 request);

    protected abstract void execute(T1 request, T2 response, List<AnyDataModel> anyDataModels);
}
