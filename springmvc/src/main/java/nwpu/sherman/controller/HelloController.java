package nwpu.sherman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring MVC hello world程序，相关处理器
 *
 * @author sherman
 */
@Controller
public class HelloController {
    /**
     * 一个基本Spring MVC工程测试
     */
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello spring mvc!";
    }

    /**
     * 跳转/WEB-INF/views/success.jsp页面，而输出json数据
     */
    @RequestMapping("/hello/success")
    public String success() {
        return "success";
    }

    /**
     * params支持简单的表达式
     */
    @ResponseBody
    @RequestMapping(value = "/param1", params = {"name!=sherman", "gender=man"})
    public String param1() {
        return "params: name!=sherman, gender=man";
    }

    /**
     * 演示Ant分割资源地址通配符：?
     */
    @ResponseBody
    @RequestMapping("/usr/createUser??")
    public String ant01() {
        return "/Ant -> ?";
    }

    /**
     * 演示Ant分割资源地址通配符：*
     */
    @ResponseBody
    @RequestMapping("/usr/*/createUser")
    public String ant02() {
        return "/Ant -> *";
    }

    /**
     * 演示Ant分割资源地址通配符：**
     */
    @ResponseBody
    @RequestMapping("/usr/**/createUser")
    public String ant03() {
        return "/Ant -> **";
    }

    /**
     * 使用@PathVariable捕获url路径中的占位符
     * 注意如果url路径为：/usr/2571/createUser，这个url也能被上面ant02()和ant03()匹配，
     * 但是运行并没有被捕获
     */
    @ResponseBody
    @RequestMapping("/usr/{user_id}/createUser")
    public String createUser(@PathVariable("user_id") int userId) {
        return "userId: " + userId;
    }
}
