package fun.sherman.propconfig.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "micro.url")
public class MicroServiceUrl {
    private String orderUrl;
    private String userUrl;
    private String shippingUrl;
}
