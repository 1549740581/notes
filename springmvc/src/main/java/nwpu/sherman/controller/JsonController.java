package nwpu.sherman.controller;

import nwpu.sherman.dao.EmployeeDao;
import nwpu.sherman.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * 返回Json数据测试
 *
 * @author sherman
 */
@Controller
public class JsonController {
    @Autowired
    private EmployeeDao employeeDao;

    @ResponseBody
    @RequestMapping("/json/all_employees")
    public Collection<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }
}
