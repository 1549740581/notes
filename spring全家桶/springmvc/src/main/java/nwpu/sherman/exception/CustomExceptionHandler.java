package nwpu.sherman.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 当异常发生时，且当前Handler中找不到@ExceptionHandler方法来处理当前异常，则会去
 * 有@ControllerAdvice注解的类中继续查找@ExceptionHandler标记的方法来处理
 *
 * 注意：
 * 演示该方法使用时，可以将ExceptionController.java中handleArithmeticException方法
 * 注释，当发生ArithmeticException，该类下到的方法仍然能够捕获到。
 *
 * @author sherman
 */
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handlerArithmeticException(Exception ex) {
        ModelAndView mv = new ModelAndView("error_page");
        mv.addObject("error", ex);
        return mv;
    }
}
