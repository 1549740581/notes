package nwpu.sherman.intercept;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author sherman
 */
@Component
public class CustomIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=======preHandle==========");
        /**
         * 如果这个直接返回false，后续的拦截器不会执行，而且该拦截器的postHandle和afterCompletion也不会执行
         * 因此可以考虑做：权限管理、事务管理等等
         */
        return true;
    }

    /**
     * 可以考虑对请求域中的属性或者视图做出修改
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("=======postHandle==========");
    }

    /**
     * 可以考虑用来释放资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("=======afterCompletion==========");
    }
}
