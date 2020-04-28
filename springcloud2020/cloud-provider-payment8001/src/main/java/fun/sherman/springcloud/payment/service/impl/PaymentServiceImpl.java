package fun.sherman.springcloud.payment.service.impl;

import fun.sherman.springcloud.commonapi.entity.Payment;
import fun.sherman.springcloud.payment.dao.PaymentDao;
import fun.sherman.springcloud.payment.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created on 2020/4/28
 *
 * @author sherman tang
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.selectById(id);
    }
}
