package com.orange.eduback.common;

import lombok.Data;

@Data
public class PlainResult<T> {
    private T data;
    private String message;
    private int code;
    private boolean success;

    public PlainResult() {
    }

    public static <T> PlainResult<T> success(T data) {
        PlainResult<T> result = new PlainResult<>();
        result.setData(data);
        result.setSuccess(true);
        result.setCode(GlobalConstants.SUCCESS_CODE);
        result.setMessage("success");
        return result;
    }

    public static <T> PlainResult<T> fail(int code,String message) {
        PlainResult<T> result = new PlainResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
