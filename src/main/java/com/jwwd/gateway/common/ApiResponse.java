package com.jwwd.gateway.common;

public record ApiResponse<T>(boolean success, String message, T data) {

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "success", data);
    }

    public static <T> ApiResponse<T> failed(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
