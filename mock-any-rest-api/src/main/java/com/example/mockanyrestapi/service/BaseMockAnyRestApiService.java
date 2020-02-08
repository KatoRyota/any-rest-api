package com.example.mockanyrestapi.service;

import com.example.anyrestapicore.bean.mockanyrestapi.request.MockAnyRestApiBaseRequestModel;
import com.example.anyrestapicore.bean.mockanyrestapi.response.MockAnyRestApiBaseResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public abstract class BaseMockAnyRestApiService<
        T1 extends MockAnyRestApiBaseRequestModel<?>,
        T2 extends MockAnyRestApiBaseResponseModel<?>> {

    private static final Logger logger = LoggerFactory.getLogger(BaseMockAnyRestApiService.class);
    private static final Logger restLogger = LoggerFactory.getLogger("rest");
    private static final Logger performanceLogger = LoggerFactory.getLogger("performance");

    public T2 process(T1 request) {
        try {
            logger.info("[start] {}", this.getClass().getCanonicalName());
            LocalTime startTime = LocalTime.now();
            restLogger.info("request->[{}]", new ObjectMapper().writeValueAsString(request));

            T2 response = createResponseModel();

            if (!validate(request, response)) {
                logger.info("Request parameter is invalid.:request->[{}],response->[{}]",
                        new ObjectMapper().writeValueAsString(request),
                        new ObjectMapper().writeValueAsString(response));

                logger.info("[end] {}", this.getClass().getCanonicalName());

                return response;
            }

            execute(request, response);

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

    protected abstract T2 createResponseModel();

    protected abstract boolean validate(T1 request, T2 response);

    protected abstract void execute(T1 request, T2 response);
}
