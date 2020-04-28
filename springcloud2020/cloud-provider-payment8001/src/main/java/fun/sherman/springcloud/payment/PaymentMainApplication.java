package fun.sherman.springcloud.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created on 2020/4/28
 *
 * @author sherman tang
 */
@MapperScan(basePackages = {"fun.sherman.springcloud.payment.dao"})
@SpringBootApplication
public class PaymentMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMainApplication.class, args);
    }
}
