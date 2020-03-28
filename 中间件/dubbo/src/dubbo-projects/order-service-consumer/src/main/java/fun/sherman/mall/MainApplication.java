package fun.sherman.mall;

import fun.sherman.mall.service.IOrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("consumer.xml");
        IOrderService iOrderService = ioc.getBean(IOrderService.class);
        iOrderService.initOrder("1");
        System.in.read();
    }
}
