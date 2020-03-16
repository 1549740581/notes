package fun.sherman.json.controller;

import fun.sherman.json.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/json")
public class HelloController {

    @RequestMapping("/str")
    public String getString() {
        return "hello spring boot";
    }

    // jackson在没用重写POJO类的toString()方法时，仍然能够转换
    // {"id":1,"name":"sherman","score":99.9}
    @RequestMapping("/user")
    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setName("sherman");
        user.setScore(99.9);
        return user;
    }

    @RequestMapping("/list")
    public List<User> getUserList() {
        List<User> arrayList = new ArrayList<>();
        arrayList.add(new User(2, "tl", 10.0));
        // 注意下面User的score为null
        arrayList.add(new User(3, "wqy", null));
        return arrayList;
    }

    @RequestMapping("/map")
    public Map<String, Object> getUserMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", new User(4, "tanglei", 30.0));
        map.put("ext", "json for map");
        return map;
    }

}
