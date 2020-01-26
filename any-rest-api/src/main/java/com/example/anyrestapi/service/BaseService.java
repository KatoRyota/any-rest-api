package com.example.anyrestapi.service;

import com.example.anyrestapicore.model.common.AnyDataModel;
import com.example.anyrestapicore.model.request.BaseRequestModel;
import com.example.anyrestapicore.model.response.BaseResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public abstract class BaseService<
        T1 extends BaseRequestModel<?>,
        T2 extends BaseResponseModel<?>> {

    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
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

        AnyDataModel anyDataModel = createIntermediateObject(request);
        execute(request, response, anyDataModel);

        restLogger.info("response->[{}]", response.toString());
        LocalTime endTime = LocalTime.now();
        long processTime = ChronoUnit.SECONDS.between(startTime, endTime);
        performanceLogger.info("processTime->[{}]", processTime);
        logger.info("[end] {}", this.getClass().getCanonicalName());

        return response;
    }

    protected abstract T2 createResponseModel();

    protected abstract boolean validate(T1 request, T2 response);

    protected abstract AnyDataModel createIntermediateObject(T1 request);

    protected abstract void execute(T1 request, T2 response, AnyDataModel anyDataModel);
}
