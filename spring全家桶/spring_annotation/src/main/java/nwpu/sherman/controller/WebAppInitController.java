package nwpu.sherman.controller;

import nwpu.sherman.service.WebAppInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 演示全注解方式启动SpringMVC是否能够正确访问
 *
 * @author sherman
 */
@Controller
public class WebAppInitController {
    @Autowired
    WebAppInitService webAppInitService;

    @ResponseBody
    @RequestMapping("/webAppInit")
    public String webAppInit() {
        String result = webAppInitService.say("sherman");
        return result;
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }
}
