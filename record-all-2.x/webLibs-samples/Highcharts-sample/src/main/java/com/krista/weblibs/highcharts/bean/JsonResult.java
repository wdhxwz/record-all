package com.krista.weblibs.highcharts.bean;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JsonResult
 *
 * @author krista
 * @version V1.0
 * @since 2019/1/26 21:40
 */
public class JsonResult<T> {
    private static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MESSAGE = "请求成功";

    private Integer code;
    private String message;
    private T data;

    public static <T> JsonResult<T> success(T data) {
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setCode(SUCCESS_CODE);
        jsonResult.setMessage(SUCCESS_MESSAGE);
        jsonResult.setData(data);

        return jsonResult;
    }

    public JsonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResult() {
    }


    public JsonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
