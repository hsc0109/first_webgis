package cn.yls.metaluccs.server.common;

public enum ReturnCodeEnum {

    SUCCESS(0,"数据获取成功"),
    ERROR(-1,"数据获取出现错误"),


    APP_KEY_NULL(401,"未提供客户端标识(appKey)"),
    SIGN_NULL(402,"未提供客户端签名(sign)"),
    UNAUTHORIZED(403,"未通过身份验证"),

    PARAM_NULL(1001,"参数不能为空")

    ;
    /**
     * 值 Integer型
     */
    private final Integer code;
    /**
     * 描述 String型
     */
    private final String message;

    ReturnCodeEnum(Integer value, String message) {
        this.code = value;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ReturnCodeEnum getStatusState(Integer value) {
        if (null == value)
            return null;
        for (ReturnCodeEnum _enum : ReturnCodeEnum.values()) {
            if (value.equals(_enum.getCode()))
                return _enum;
        }
        return null;
    }

    public static ReturnCodeEnum getStatusState(String description) {
        if (null == description)
            return null;
        for (ReturnCodeEnum _enum : ReturnCodeEnum.values()) {
            if (description.equals(_enum.getMessage()))
                return _enum;
        }
        return null;
    }

}
