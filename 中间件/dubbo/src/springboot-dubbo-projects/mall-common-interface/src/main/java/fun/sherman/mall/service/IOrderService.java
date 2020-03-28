package fun.sherman.mall.service;

import fun.sherman.mall.bean.UserAddress;

import java.util.List;

public interface IOrderService {
    List<UserAddress> initOrder(String userId);
}
