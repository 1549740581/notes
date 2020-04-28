package fun.sherman.springcloud.payment.controller;

import fun.sherman.springcloud.commonapi.common.ServerResponse;
import fun.sherman.springcloud.commonapi.entity.Payment;
import fun.sherman.springcloud.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created on 2020/4/28
 *
 * @author sherman tang
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/save")
    public ServerResponse<Integer> save(@RequestBody Payment payment) {
        int result = paymentService.insert(payment);
        log.info("*********************************");
        if (result > 0) {
            return ServerResponse.success("插入订单成功", result);
        } else {
            return ServerResponse.fail("保存订单失败");
        }
    }

    @GetMapping("/get/{id}")
    public ServerResponse<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
            return ServerResponse.fail("获取订单失败");
        } else {
            return ServerResponse.success("获取订单成功", payment);
        }
    }
}
