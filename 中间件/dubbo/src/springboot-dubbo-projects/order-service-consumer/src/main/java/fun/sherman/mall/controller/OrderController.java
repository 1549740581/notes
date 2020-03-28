package fun.sherman.mall.controller;

import fun.sherman.mall.bean.UserAddress;
import fun.sherman.mall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class OrderController {
    @Autowired
    private IOrderService iOrderService;

    @GetMapping("/user_address")
    public List<UserAddress> getUserAddressList(@RequestParam("uid") String userId) {
        return iOrderService.initOrder(userId);
    }
}
