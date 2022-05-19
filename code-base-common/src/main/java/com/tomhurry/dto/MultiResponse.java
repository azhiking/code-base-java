package com.tomhurry.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 多结果返回
 *
 * @author taozhi
 * @date 2022/5/19 14:28
 * @since 1.0.0
 */
public class MultiResponse<T> extends Response {

    private static final long serialVersionUID = 1L;

    private Collection<T> data;

    public List<T> getData() {
        return null == data ? Collections.emptyList() : new ArrayList<>(data);
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public boolean isEmpty() {
        return data == null || data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public static MultiResponse<?> buildSuccess() {
        return buildMultiResponse(true, null, null, null);
    }

    public static <T> MultiResponse<T> buildSuccess(Collection<T> data) {
        return buildMultiResponse(true, null, null, data);
    }

    public static MultiResponse<?> buildFailure(String errCode, String errMessage) {
        return buildMultiResponse(false, errCode, errMessage, null);
    }

    public static <T> MultiResponse<T> buildMultiResponse(boolean success, String code, String message, Collection<T> data) {
        MultiResponse<T> response = new MultiResponse<>();
        response.setSuccess(success);
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
