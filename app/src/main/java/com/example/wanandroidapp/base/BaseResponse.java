package com.example.wanandroidapp.base;

/**
 * Observerser的基础接口
 *
 * @author: ODM
 * @date: 2019/7/12
 */
public class BaseResponse<T> {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    /**
     * 0：成功，1：失败
     */
    private int errorCode;

    private String errorMsg;

    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
