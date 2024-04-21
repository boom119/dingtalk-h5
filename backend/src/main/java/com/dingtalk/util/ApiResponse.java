/**
 * @author OpenTheDoor
 * @date 2024/4/21 15:05
 * @version 1.0
 */
package com.dingtalk.util;

import lombok.Data;

/**
 * 通用的API响应结构体
 */
@Data
public class ApiResponse<T> {
    private int code; // 状态码
    private String message; // 响应消息
    private T data; // 响应数据

    // 构造函数
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 快速创建成功响应的方法
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "Success", data);
    }

    // 快速创建失败响应的方法
    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}


