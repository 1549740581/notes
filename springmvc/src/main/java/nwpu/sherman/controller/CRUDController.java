package nwpu.sherman.controller;

import nwpu.sherman.dao.DepartmentDao;
import nwpu.sherman.dao.EmployeeDao;
import nwpu.sherman.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * CRUD操作控制器
 *
 * @author sherman
 */

@Controller
public class CRUDController {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 获取所有的employees并保存到request域中
     */
    @RequestMapping("/crud/emps")
    public String getAllEmployees(Map<String, Object> maps) {
        maps.put("employees", employeeDao.getAll());
        return "/crud/list_employees";
    }

    /**
     * 添加新员工，第一步：GET方式到添加页面
     */
    @RequestMapping(value = "/crud/emp", method = RequestMethod.GET)
    public String addEmployeeGET(Map<String, Object> maps) {
        maps.put("departments", departmentDao.getDepartments());
        /**
         * modelAttribute默认从request域对象中获取，否则报错
         * 因此即使第一次访问，也应该向Map中put一个空的Employee对象，且key和
         * modelAttribute属性相同
         */
        maps.put("employee", new Employee());
        return "/crud/add_employee";
    }

    /**
     * 添加新员工，第二步：POST方式提交表单
     */
    @RequestMapping(value = "/crud/emp", method = RequestMethod.POST)
    public String addEmployeePOST(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/crud/emps";
    }

    /**
     * 删除给定id的employee
     */
    @RequestMapping(value = "/crud/emp/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/crud/emps";
    }

    /**
     * 修改employee信息，第一步：先回显员工信息
     */
    @RequestMapping(value = "/crud/emp/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable("id") Integer id, Map<String, Object> maps) {
        maps.put("employee", employeeDao.getEmployee(id));
        maps.put("departments", departmentDao.getDepartments());
        return "/crud/add_employee";
    }

    /**
     * 修改employee信息，第二步：更新employee信息
     */
    @RequestMapping(value = "/crud/emp", method = RequestMethod.PUT)
    public String updateEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/crud/emps";
    }

    /**
     * 不更新LastName属性，因此需要使用@ModelAttribute注解
     */
    @ModelAttribute("employee")
    public void getEmployeeMA(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> maps) {
        if (id != null) {
            maps.put("employee", employeeDao.getEmployee(id));
        }
    }

    /**
     * 使用@InitBinder注解在对把表单提交的JavaBean对象的字段进行绑定处理
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // 忽略表单提交的lastName字段
        webDataBinder.setDisallowedFields("lastName");
    }
}

