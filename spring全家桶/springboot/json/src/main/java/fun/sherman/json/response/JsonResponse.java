package fun.sherman.json.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 发回给客户端的Json响应对象
 * @param <T>
 */
@Getter
@Setter
public class JsonResponse<T> {
    private int code;
    private String msg;
    private T data;

    private JsonResponse(int code) {
        this(code, null, null);
    }

    private JsonResponse(int code, String msg) {
        this(code, msg, null);
    }

    private JsonResponse(int code, T data) {
        this(code, null, data);
    }

    private JsonResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private JsonResponse(ResponseCode rc) {
        this(rc.getCode(), rc.getMsg(), null);
    }

    public static <T> JsonResponse<T> buildSuccess() {
        return new JsonResponse<>(ResponseCode.SUCCESS);
    }

    public static <T> JsonResponse<T> buildSuccessWithMsg(String msg) {
        return new JsonResponse<>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> JsonResponse<T> buildSuccessWithData(T data) {
        return new JsonResponse<>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> JsonResponse<T> buildSuccessWithMsgAndData(String msg, T data) {
        return new JsonResponse<>(ResponseCode.SUCCESS.getCode(), msg, data);
    }


    public static <T> JsonResponse<T> buildError() {
        return buildError(ResponseCode.FAIL);
    }

    public static <T> JsonResponse<T> buildError(ResponseCode rc) {
        return new JsonResponse<>(rc);
    }

    public static <T> JsonResponse<T> buildErrorWithMsg(String msg) {
        return buildErrorWithMsgAndData(ResponseCode.FAIL.getCode(), msg);
    }

    public static <T> JsonResponse<T> buildErrorWithMsgAndData(int code, String msg) {
        return new JsonResponse<>(code, msg);
    }
}
