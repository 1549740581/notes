package nwpu.sherman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 演示HiddenHttpMethodFilter配合form表单提交post请求，
 * post请求时候携带_method隐藏域，将请求方式的值改成delete或者put请求
 * 注意需要在web.xml对HiddenHttpMethodFilter进行配置
 *
 * @author sherman
 */
@Controller
public class HiddenHttpMethodFilterController {
    /**
     * form表单支持get和post请求，url直接访问/rest/test路径就是get请求
     */
    @ResponseBody
    @RequestMapping(value = "/rest/test", method = RequestMethod.GET)
    public String get() {
        return "get";
    }

    /**
     * form表单支持get和post请求，提交表单时候选择post请求
     */
    @ResponseBody
    @RequestMapping(value = "/rest/test", method = RequestMethod.POST)
    public String post() {
        return "post";
    }

    /**
     * form表单不支持put请求，但是可以配合隐藏域_method，将post请求转换成put请求
     */
    @ResponseBody
    @RequestMapping(value = "/rest/test", method = RequestMethod.PUT)
    public String put() {
        return "put";
    }

    /**
     * form表单不支持put请求，但是可以配合隐藏域_method，将post请求转换成delete请求
     */
    @ResponseBody
    @RequestMapping(value = "/rest/test", method = RequestMethod.DELETE)
    public String delete() {
        return "delete";
    }
}
