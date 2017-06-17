package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by buzheng on 17/6/17.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//如果内容为空  则把key消失掉
public class ServerResponse<T> implements Serializable{

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status) {
        this.status= status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess(){

        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());

    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg,T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }


    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }


    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }


    /**
     * 响应是否成功
     * @return json序列化不显示
     */
    @JsonIgnore
    public boolean isSuccess(){
        return status==ResponseCode.SUCCESS.getCode();
    }
}
