package nwpu.sherman.config;

import nwpu.sherman.aop.LogAspect;
import nwpu.sherman.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author sherman
 */

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean("mathCalculator")
    public MathCalculator getMathCalculator() {
        return new MathCalculator();
    }

    @Bean("logAspect")
    public LogAspect getLogAspect() {
        return new LogAspect();
    }
}
