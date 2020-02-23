package nwpu.sherman.controller;

import nwpu.sherman.domain.ValidateEmployee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

/**
 * 数据验证测试Controller
 *
 * @author sherman
 */
@Controller
public class ValidateEmployeeController {
    @RequestMapping("/validate/validate_employee")
    public String validateEmployee(Map<String, Object> maps) {
        maps.put("validateEmployee", new ValidateEmployee());
        return "crud/add_validate_employee";
    }

    @RequestMapping(value = "/validate/show_validate_employee", method = RequestMethod.POST)
    public String showValidateEmployee(@Valid ValidateEmployee validateEmployee, BindingResult bindingResult) {
        if(bindingResult.getErrorCount() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            }
        }
        System.out.println(validateEmployee);
        return "crud/add_validate_employee";
    }
}
