package nwpu.sherman.controller;

import nwpu.sherman.domain.Address;
import nwpu.sherman.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 处理模型数据的四种方法：
 * 1、ModelAndView
 * 2、Map & Model & ModelMap
 * 3、SessionAttributes
 * 4. @ModelAttribute
 *
 * @author sherman
 */
@SessionAttributes(value = {"addr"}, types = Long.class)
@Controller
public class HandleModelController {
    public static final String VIEW_NAME = "success";

    /**
     * 如果返回值是ModelAndView，则返回结果既包含了视图信息，又被包含了模型数据信息，
     * Spring MVC会把ModelAndView的model数据放入到request域中
     */
    @RequestMapping("/test/model/model_and_view")
    public ModelAndView testModelAndView() {
        ModelAndView modelAndView = new ModelAndView(VIEW_NAME);
        modelAndView.addObject("time", new Date());
        return modelAndView;
    }

    /**
     * 目标方法可以添加Map、Model类型以及ModelMap类型参数，整个Map也会被添加到request域中
     */
    @RequestMapping("/test/model/map")
    public String testMap(Map<String, Object> maps) {
        // org.springframework.validation.support.BindingAwareModelMap
        System.out.println(maps.getClass().getName());
        maps.put("names", Arrays.asList("sherman", "tl"));
        return VIEW_NAME;
    }

    /**
     * 演示@SessionAttributes注解将Address对象和Long对象放入到session中
     */
    @RequestMapping("/test/model/session_attributes")
    public String testSessionAttributes(Map<String, Object> maps) {
        maps.put("addr", new Address("Shaanxi", "Xi'an"));
        maps.put("magic", 2571L);
        return VIEW_NAME;
    }

    /**
     * 测试@ModelAttribute对数据库中取出的数据进行部分更新
     */
    @RequestMapping("/test/model/model_attribute")
    public String testModelAttribute(@ModelAttribute/*(value = "foo_user")*/ User user, Map<String, Object> maps) {
        maps.put("modified", user);
        return VIEW_NAME;
    }


    /**
     * 模拟从数据库中取出的一条记录的过程
     * 该方法使用@ModelAttribute注解，每次调用各个目标控制器方法之前，
     * 该方法都会先执行
     */
    @ModelAttribute
    private void getUserFromDB(Map<String, Object> maps) {
        // 这里直接new一个User模拟从数据库中获取一个User
        User user = new User(1, "tl", new Address());
        /**
         * 如果目标方法入参没有使用@ModelAttribute注解中value属性指定，
         * 则maps中put进去的key名称必须和目标方法入参类型首字母小写一致，即：
         * maps.put("user", user) ----> testModelAttribute(@ModelAttribute User user)，两个user要一致
         *
         * 或者使用@ModelAttribute注解中value属性进行指定：
         * maps.put("foo_user", user)
         */
        maps.put("user", user);
        maps.put("original", user.toString());
    }
}
