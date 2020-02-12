package nwpu.sherman.controller;

import nwpu.sherman.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义ConversionService对应的测试Controller：
 * 将字符串直接转换成Employee对象
 *
 * @author sherman
 */
@Controller
public class ConvertController {
    @RequestMapping("/cvt/convert_service")
    @ResponseBody
    public String convertStringToEmployee(@RequestParam("employee") Employee employee) {
        return employee.toString();
    }
}
