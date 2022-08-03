package com.limitway.current.common;

public class Constants {

    // 请求处理成功
    public static final Integer RESPONSE_CODE_OK = 200;
    // 请求处理失败
    public static final Integer RESPONSE_CODE_ERROR = 500;
    // 非法TOKEN
    public static final Integer RESPONSE_CODE_TOKEN_ILLEGAL = 508;
    // 被踢出
    public static final Integer RESPONSE_CODE_TOKEN_KICKOUT = 512;
    // TOKEN超时
    public static final Integer RESPONSE_CODE_TOKEN_EXPIRED = 514;

    // 密码salt
    public static final String PASSWORD_SALT = "8ab3e942-7927-11e9-9b87-43d45f5ebb8f";

    //-------------redis key prefix
    public static final String REDIS_PREFIX_LOGIN = "login_";

    public static final String REQUEST_HEADER_TOKEN = "X-Token";
}
