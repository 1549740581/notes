package nwpu.sherman.controller;

import nwpu.sherman.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * POJO映射的Controller测试
 *
 * @author sherman
 */
@Controller
public class PojoMappingController {
    /**
     * 表单提交的属性要和方法入参对应
     */
    @RequestMapping(value = "/test/pojo", method = RequestMethod.POST)
    @ResponseBody
    public String testPojoMapping(User user) {
        return user.toString();
    }

    /**
     * 演示原生的Servlet API作为方法入参
     */
    @RequestMapping(value = "/test/native_servlet_api", method = RequestMethod.GET)
    @ResponseBody
    public String testNativeServletAPI(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        return "request: " + request + ", response: " + response + ", locale: " + locale;
    }
}
