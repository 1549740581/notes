package nwpu.sherman.service;

import org.springframework.stereotype.Service;

/**
 * @author sherman
 *
 * 辅助全注解的SpringMVC测试，有WebAppInitController调用
 */
@Service
public class WebAppInitService {
    public String say(String name) {
        return "hello: " + name;
    }
}
