package cn.yls.metaluccs.server.common;

public class Result<T> {

    private Integer returnCode;

    private T data;

    private String message;

    public Result() {
    }

    public Result(Integer returnCode, T data, String message) {
        this.returnCode = returnCode;
        this.data = data;
        this.message = message;
    }

    public Result(Integer returnCode, String message) {
        this.returnCode = returnCode;
        this.message = message;
    }

    public Result(ReturnCodeEnum returnCode, T data) {
        this.returnCode = returnCode.getCode();
        this.message = returnCode.getMessage();
        this.data = data;
    }

    public Result(ReturnCodeEnum returnCode) {
        this.returnCode = returnCode.getCode();
        this.message = returnCode.getMessage();
    }

    public Integer getCode() {
        return this.returnCode;
    }

    public void setCode(Integer code) {
        this.returnCode = code;
    }

    public void setData(T msg) {
        this.data = msg;
    }

    public T getData() {
        return this.data;
    }

//    public String toJson() {
//        return (new JsonMapper()).toJson(this);
//    }

    public Object getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result<T> result(ReturnCodeEnum returnCode, String customizeDescription) {
        this.returnCode = returnCode.getCode();
        this.message = customizeDescription;
        return this;
    }

    public Result<T> result(ReturnCodeEnum returnCode) {
        this.returnCode = returnCode.getCode();
        this.message = returnCode.getMessage();
        return this;
    }

    public Result<T> successData(T t) {
        this.returnCode = ReturnCodeEnum.SUCCESS.getCode();
        this.message = ReturnCodeEnum.SUCCESS.getMessage();
        this.data = t;
        return this;
    }

    public static Result success() {
        return new Result(ReturnCodeEnum.SUCCESS.getCode(), (Object)null, ReturnCodeEnum.SUCCESS.getMessage());
    }

    public Result<T> success(T msg) {
        return new Result(ReturnCodeEnum.SUCCESS.getCode(), msg, ReturnCodeEnum.SUCCESS.getMessage());
    }

    public boolean getSuccess() {
        return this.returnCode == ReturnCodeEnum.SUCCESS.getCode();
    }
}