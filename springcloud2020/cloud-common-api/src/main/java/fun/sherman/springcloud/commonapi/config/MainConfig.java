package fun.sherman.springcloud.commonapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 2020/4/29
 *
 * @author sherman tang
 */
@Configuration
public class MainConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
