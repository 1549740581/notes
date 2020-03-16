package fun.sherman.json.response;

import lombok.Getter;

/**
 * 响应状态码
 */
@Getter
public enum ResponseCode {
    SUCCESS(1, "success"),
    FAIL(-1, "fail"),

    ILLEGAL_ARGUMENT(201, "illegal argument"),
    NEED_LOGIN(201, "need login")

    // 其它错误可以继续封装...
    ;
    private final int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
}
