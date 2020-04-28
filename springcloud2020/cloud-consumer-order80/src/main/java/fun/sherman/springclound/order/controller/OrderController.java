package fun.sherman.springclound.order.controller;

import fun.sherman.springcloud.commonapi.common.ServerResponse;
import fun.sherman.springclound.order.entity.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created on 2020/4/29
 *
 * @author sherman tang
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    public static final String PAYMENT_URL = "http://localhost:8001";

    @PostMapping("/save")
    public ServerResponse create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/save", payment, ServerResponse.class);
    }

    @GetMapping("/get/{id}")
    public ServerResponse getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, ServerResponse.class);
    }
}

