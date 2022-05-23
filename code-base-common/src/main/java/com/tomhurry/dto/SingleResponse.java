package com.tomhurry.dto;

/**
 * 单个结果返回
 *
 * @author taozhi
 * @date 2022/5/19 13:56
 * @since 1.0.0
 */
public class SingleResponse<T> extends Response {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static SingleResponse<?> buildSuccess() {
        return buildSingleResponse(true, null, null, null);
    }

    public static <T> SingleResponse<T> buildSuccess(T data) {
        return buildSingleResponse(true, null, null, data);
    }

    public static SingleResponse<?> buildFailure(String code, String message) {
        return buildSingleResponse(false, code, message, null);
    }

    public static <T> SingleResponse<T> buildSingleResponse(boolean success, String code, String message, T data) {
        SingleResponse<T> response = new SingleResponse<>();
        response.setSuccess(success);
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
