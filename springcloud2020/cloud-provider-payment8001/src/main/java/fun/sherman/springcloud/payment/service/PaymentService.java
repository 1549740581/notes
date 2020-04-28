package fun.sherman.springcloud.payment.service;

import fun.sherman.springcloud.commonapi.entity.Payment;

/**
 * Created on 2020/4/28
 *
 * @author sherman tang
 */
public interface PaymentService {
    int insert(Payment payment);

    Payment getPaymentById(Long id);
}
