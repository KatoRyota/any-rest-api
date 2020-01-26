package com.example.mockanyrestapi.service;

import com.example.anyrestapicore.model.mockanyrestapi.request.BaseMockAnyRestApiRequestModel;
import com.example.anyrestapicore.model.mockanyrestapi.response.BaseMockAnyRestApiResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public abstract class BaseMockAnyRestApiService<
        T1 extends BaseMockAnyRestApiRequestModel<?>,
        T2 extends BaseMockAnyRestApiResponseModel<?>> {

    private static final Logger logger = LoggerFactory.getLogger(BaseMockAnyRestApiService.class);
    private static final Logger restLogger = LoggerFactory.getLogger("rest");
    private static final Logger performanceLogger = LoggerFactory.getLogger("performance");

    public T2 process(T1 request) {

        logger.info("[start] {}", this.getClass().getCanonicalName());
        LocalTime startTime = LocalTime.now();
        restLogger.info("request->[{}]", request.toString());

        T2 response = createResponseModel();

        if (!validate(request, response)) {
            logger.info("Request parameter is invalid.:request->[{}],response->[{}]",
                    request.toString(), response.toString());
            logger.info("[end] {}", this.getClass().getCanonicalName());

            return response;
        }

        execute(request, response);

        restLogger.info("response->[{}]", response.toString());
        LocalTime endTime = LocalTime.now();
        long processTime = ChronoUnit.SECONDS.between(startTime, endTime);
        performanceLogger.info("processTime->[{}]", processTime);
        logger.info("[end] {}", this.getClass().getCanonicalName());

        return response;
    }

    protected abstract T2 createResponseModel();

    protected abstract boolean validate(T1 request, T2 response);

    protected abstract void execute(T1 request, T2 response);
}
