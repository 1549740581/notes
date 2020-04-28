package fun.sherman.springcloud.commonapi.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回类
 * Created on 2020/4/28
 *
 * @author sherman tang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public ServerResponse(Integer code, String msg) {
        this(code, msg, null);
    }

    /**
     * 失败直接返回状态码400
     */
    public static <T> ServerResponse<T> fail(String msg) {
        return new ServerResponse<>(400, msg, null);
    }

    /**
     * 成功返回状态码200和数据信息data
     */
    public static <T> ServerResponse<T> success(String msg, T data) {
        return new ServerResponse<>(200, msg, data);
    }
}
