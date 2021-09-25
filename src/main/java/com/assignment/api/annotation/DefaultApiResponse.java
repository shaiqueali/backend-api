package com.assignment.api.annotation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.assignment.api.constant.SwaggerConstant.FORBIDDEN_REQUEST_PHRASE;
import static com.assignment.api.constant.SwaggerConstant.GATEWAY_TIMEOUT_REQUEST_PHRASE;
import static com.assignment.api.constant.SwaggerConstant.INVALID_REQUEST;
import static com.assignment.api.constant.SwaggerConstant.SUCCESS_PHRASE;
import static com.assignment.api.constant.SwaggerConstant.UNAUTHORIZED_REQUEST_PHRASE;
import static com.assignment.api.constant.SwaggerConstant.UN_PROCESSABLE_REQUEST_PHRASE;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = SUCCESS_PHRASE),
        @ApiResponse(code = 400, message = INVALID_REQUEST),
        @ApiResponse(code = 400, message = UN_PROCESSABLE_REQUEST_PHRASE),
        @ApiResponse(code = 401, message = UNAUTHORIZED_REQUEST_PHRASE),
        @ApiResponse(code = 403, message = FORBIDDEN_REQUEST_PHRASE),
        @ApiResponse(code = 502, message = GATEWAY_TIMEOUT_REQUEST_PHRASE),
})
public @interface DefaultApiResponse {
}
