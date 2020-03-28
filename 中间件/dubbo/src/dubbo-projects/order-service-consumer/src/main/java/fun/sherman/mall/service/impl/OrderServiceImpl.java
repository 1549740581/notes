package fun.sherman.mall.service.impl;

import fun.sherman.mall.bean.UserAddress;
import fun.sherman.mall.service.IOrderService;
import fun.sherman.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1. 将服务提供者注册到注册中心
 * 2. 将服务消费者去服务中心订阅
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IUserService iUserService;

    public void initOrder(String userId) {
        // 1. 查询用户的收货地址：涉及到远程调用
        List<UserAddress> userAddressList = iUserService.getUserAddressList(userId);
        userAddressList.forEach(System.out::println);
    }
}
