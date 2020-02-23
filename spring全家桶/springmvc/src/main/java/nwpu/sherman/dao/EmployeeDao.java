package nwpu.sherman.dao;

import nwpu.sherman.domain.Department;
import nwpu.sherman.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工实体类的Dao层
 *
 * @author sherman
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    private Integer initId = 1006;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>();
        employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101, "Java")));
        employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1, new Department(102, "C")));
        employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0, new Department(103, "C++")));
        employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0, new Department(104, "Python")));
        employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1, new Department(105, "Go")));
    }

    /**
     * 查询所有员工信息
     */
    public Collection<Employee> getAll() {
        return employees.values();
    }

    /**
     * 根据id查询某个员工
     */
    public Employee getEmployee(Integer id) {
        return employees.get(id);
    }

    /**
     * 根据id删除某个员工
     */
    public void delete(Integer id) {
        employees.remove(id);
    }

    /**
     * 保存员工
     */
    public void save(Employee employee) {
        if (employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }
}
