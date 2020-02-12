package nwpu.sherman.controller;

import nwpu.sherman.exception.CustomResponseStatusException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sherman
 */
@Controller
public class ExceptionController {
    @RequestMapping("/exception_handler")
    public String handleException(@RequestParam("num") int num) {
        System.out.println(10 / num);
        return "success";
    }

    /**
     * 当发生异常时，会寻找@ExceptionHandler注解标注的方法去处理
     * 注意如果要保存异常，不用使用Map（不能成功），需要使用ModelAndView
     */
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handleArithmeticException(Exception ex) {
        ModelAndView mv = new ModelAndView("error_page");
        mv.addObject("error", ex);
        return mv;
    }

    /**
     * 测试ResponseStatusExceptionResolver
     */
    @RequestMapping("/responseStatusExceptionResolver")
    public String testResponseStatusExceptionResolver(@RequestParam("num") int num) {
        if (10 == num) {
            throw new CustomResponseStatusException();
        }
        return "error_page";
    }

    /**
     * 测试SimpleMappingExceptionResolver
     */
    @RequestMapping("/simpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("len") int len) {
        /**
         * 当传递过来的参数len>=10，抛出数组越界异常
         */
        String[] strs = new String[10];
        System.out.println(strs[len]);
        return "error_page";
    }
}
