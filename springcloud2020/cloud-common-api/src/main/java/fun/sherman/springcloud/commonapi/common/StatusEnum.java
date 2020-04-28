package fun.sherman.springcloud.commonapi.common;

/**
 * 通用状态码封装
 * Created on 2020/4/28
 *
 * @author sherman tang
 */
public enum StatusEnum {
    // 正常成功、失败
    NORMAL_SUCCESS(200, "调用正常"),
    NORMAL_FAIL(400, "调用失败"),

    // 权限校验100xxx
    UN_AUTHORITY(100401, "未验证"),
    FORBIDDEN(100402, "禁止登陆"),

    // 服务端校验200xxx
    TIMEOUT(200101, "调用超时"),
    BAD_GATEWAY(200102, "网关错误")

    //...
    ;

    private Integer code;
    private String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String codeOf(Integer code) {
        for (StatusEnum value : values()) {
            if (value.code.equals(code)) {
                return value.msg;
            }
        }
        return null;
    }
}
