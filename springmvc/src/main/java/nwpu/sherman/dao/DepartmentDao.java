package nwpu.sherman.dao;

import nwpu.sherman.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 部门实体类的Dao层，这里实际使用static块模拟从数据库中获取数据
 *
 * @author sherman
 */
@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments = null;

    static {
        departments = new HashMap<>();
        departments.put(101, new Department(101, "Java"));
        departments.put(102, new Department(102, "C"));
        departments.put(103, new Department(103, "C++"));
        departments.put(104, new Department(104, "Python"));
        departments.put(105, new Department(105, "Go"));
    }

    /**
     * 查询所有部门
     */
    public Collection<Department> getDepartments() {
        return departments.values();
    }

    /**
     * 根据id查询部门
     */
    public Department getDepartment(Integer id) {
        return departments.get(id);
    }
}
