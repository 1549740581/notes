package fun.sherman.propconfig.controller;

import fun.sherman.propconfig.pojo.MicroServiceUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Value("${url.orderUrl}")
    private String orderUrl;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("config")
    public String getOrderUlr() {
        log.info("=======获取订单地址: {}", orderUrl);
        return "config";
    }

    @Resource
    private MicroServiceUrl microServiceUrl;

    @RequestMapping("micro_service_url")
    public String getMicroServiceUrl() {
        log.info("========MicroServiceUrl: {}", microServiceUrl.toString());
        return "micro_service_url";
    }
}
