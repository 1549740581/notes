package nwpu.sherman.controller;

import nwpu.sherman.domain.FormatEmployee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 数据格式化对应的处理器
 *
 * @author sherman
 */
@Controller
public class FormatController {
    @RequestMapping("/format/format_employee")
    public String formatEmployee(Map<String, Object> maps) {
        maps.put("formatEmployee", new FormatEmployee());
        return "crud/add_format_employee";
    }

    @ResponseBody
    @RequestMapping(value = "/format/show_format_employee", method = RequestMethod.POST)
    public String showFormatEmployee(FormatEmployee formatEmployee, BindingResult bindingResult) {
        /**
         * 如果格式转化错误，使用BindingResult来接受错误信息
         */
        if (bindingResult.getErrorCount() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            }
        }
        return formatEmployee.toString();
    }
}
