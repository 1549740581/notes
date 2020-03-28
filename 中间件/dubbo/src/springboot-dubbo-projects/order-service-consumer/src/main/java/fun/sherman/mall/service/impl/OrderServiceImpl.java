package fun.sherman.mall.service.impl;

import fun.sherman.mall.bean.UserAddress;
import fun.sherman.mall.service.IOrderService;
import fun.sherman.mall.service.IUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Reference(version = "1.0.0")
    private IUserService iUserService;

    @Override
    public List<UserAddress> initOrder(String userId) {
        return iUserService.getUserAddressList(userId);
    }
}
