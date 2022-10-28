package com.sakura.web.common;

import lombok.Data;

/**
 * 用来返回 响应 信息的 集合的
 * @param <T>
 */
@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result();

        result.setCode("0");
        result.setMsg("成功");

        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    /**
     * 返回错误信息
     * @param code 错误码
     * @param msg 信息
     * @return 错误信息
     */
    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
