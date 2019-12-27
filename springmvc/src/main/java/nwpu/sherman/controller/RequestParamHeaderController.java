package nwpu.sherman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 测试请求参数和请求头：@RequestParam & @RequestHeader
 *
 * @author sherman
 */
@Controller
public class RequestParamHeaderController {

    /**
     * 请求url为/test/request_param，且必须包含name参数，age不指定默认为23
     */
    @ResponseBody
    @RequestMapping(value = "/test/request_param", method = RequestMethod.GET)
    public String testRequestParam(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "age", required = false, defaultValue = "23") int age) {
        return "name: " + name + ", age: " + age;
    }

    /**
     * 请求url为/test/request_header，且请求头中必须包含Language和Host
     */
    @ResponseBody
    @RequestMapping(value = "/test/request_header", method = RequestMethod.GET)
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String language,
                                    @RequestHeader(value = "Host") String host) {
        return "Language: " + language + ", Host: " + host;
    }

    /**
     * 请求url为/test/cookie_value，获取请求中JSESSIONID
     */
    @ResponseBody
    @RequestMapping(value = "/test/cookie_value", method = RequestMethod.GET)
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
        return "JSESSIONID: " + sessionId;
    }
}
