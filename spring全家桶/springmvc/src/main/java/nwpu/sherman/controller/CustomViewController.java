package nwpu.sherman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1、自定义视图相关的控制器，注意该控制器返回的是自定义视图的名称，默认类名首字母小写
 * 2、演示转发和重定向
 *
 * @author sherman
 */
@Controller
public class CustomViewController {
    /**
     * 访问自定义视图
     */
    @RequestMapping("/view/custom_view")
    public String testCustomView() {
        return "customView";
    }

    /**
     * 转发
     * 注意url地址变化：
     * localhost:8080/index.jsp -> localhost:8080/view/forward
     */
    @RequestMapping("/view/forward")
    public String testForward() {
        return "forward:/views/forward.jsp";
    }

    /**
     * 重定向
     * 注意url地址变化：
     * localhost:8080/index.jsp -> localhost:8080/views/redirect.jsp
     */
    @RequestMapping("/view/redirect")
    public String testRedirect() {
        return "redirect:/views/redirect.jsp";
    }

    /**
     * 转发，且转发路径在WEB-INF下，没有影响
     * 注意url地址变化：
     * localhost:8080/index.jsp -> localhost:8080/view/web_inf/forward
     */
    @RequestMapping("/view/web_inf/forward")
    public String testWebInfForward() {
        return "forward:/WEB-INF/views/success.jsp";
    }

    /**
     * 重定向，且转发路径在WEB-INF下，404！
     * 原因：
     *
     * 重定向发生在客户端，即redirect的路径就相当于直接在url地址栏中输入对应的地址
     * WEB-INF目录下的文件有保护，转发可以访问，但是直接访问不行（重定向就相当于直接访问！）
     *
     * 注意url地址变化：
     * localhost:8080/index.jsp -> localhost:8080/WEB-INF/views/success.jsp
     */
    @RequestMapping( "/view/web_inf/redirect")
    public String testWebInfRedirect() {
        return "redirect:/WEB-INF/views/success.jsp";
    }
}
