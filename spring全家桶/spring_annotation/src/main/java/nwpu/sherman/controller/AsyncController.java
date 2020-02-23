package nwpu.sherman.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * 使用Callable接口完成SpringMVC异步处理请求
 *
 * @author sherman
 */

@Controller
public class AsyncController {

    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> asyncController() {
        System.out.println(Thread.currentThread().getName() + ": 开始工作..." + System.currentTimeMillis());
        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + ": 开始工作..." + System.currentTimeMillis());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 结束工作..." + System.currentTimeMillis());
            return "Callable<String> asyncController()";
        };
        System.out.println(Thread.currentThread().getName() + ": 结束工作..." + System.currentTimeMillis());
        return callable;
    }
}
