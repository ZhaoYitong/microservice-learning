package com.microservice.common.constants;

/**
 * 服务常量定义
 */
public class ServiceConstants {
    // 服务名称
    public static final String SERVICE_USER = "service-user";
    public static final String SERVICE_ORDER = "service-order";
    public static final String SERVICE_PRODUCT = "service-product";

    // HTTP 常量
    public static final String HEADER_USER_ID = "X-User-Id";
    public static final String HEADER_REQUEST_ID = "X-Request-Id";

    // 响应状态码
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;
    public static final int NOT_FOUND_CODE = 404;
    public static final int UNAUTHORIZED_CODE = 401;
}
