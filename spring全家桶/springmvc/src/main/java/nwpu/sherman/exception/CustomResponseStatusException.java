package nwpu.sherman.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 将异常转换成Http status
 *
 * @author sherman
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "用户名和密码不匹配")
public class CustomResponseStatusException extends RuntimeException {
}
