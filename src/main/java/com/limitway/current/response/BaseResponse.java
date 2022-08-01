package com.crawl.response;

import com.crawl.common.Constants;
import lombok.Data;

/**
 * BaseResponse
 *
 * @author JackyChang
 * @since in 10:35 2019/5/20
 */
@Data
public class BaseResponse<T> {

    private Integer code;
    private String message;
    private T data;

    private BaseResponse() {

    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(Constants.RESPONSE_CODE_OK, "success");
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(Constants.RESPONSE_CODE_OK, "success", data);
    }

    public static BaseResponse error() {
        return new BaseResponse(Constants.RESPONSE_CODE_ERROR, "success");
    }

    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse<>(Constants.RESPONSE_CODE_ERROR, "success", data);
    }

}
