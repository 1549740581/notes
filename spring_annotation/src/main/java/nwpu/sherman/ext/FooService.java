package nwpu.sherman.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * 一个普通的业务逻辑组件，配合@EventListener注解也能完成事件监听工作
 * Service组件放在ext包下只是为了演示作用，方便包扫描能够扫到
 *
 * @author sherman
 */
@Service
public class FooService {
    @EventListener
    public void fooService(ApplicationEvent event) {
        System.out.println("#### FooService: " + event);
    }
}
