package nwpu.sherman.convert;

import nwpu.sherman.domain.Department;
import nwpu.sherman.domain.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 自定义ConversionService，将字符串转换成Employee对象
 *
 * @author sherman
 */
@Component
public class CustomConversionService implements Converter<String, Employee> {
    /**
     * 将字符串转换成Employee对象
     */
    @Override
    public Employee convert(String source) {
        if (source != null) {
            // 格式：lastName-email-gender-department.id
            String[] arr = source.split("-");
            if (arr.length != 4) {
                return null;
            }
            Employee employee = new Employee();
            employee.setLastName(arr[0]);
            employee.setEmail(arr[1]);
            employee.setGender(Integer.parseInt(arr[2]));
            Department dept = new Department();
            dept.setId(Integer.parseInt(arr[3]));
            employee.setDepartment(dept);
            return employee;
        }
        return null;
    }
}
