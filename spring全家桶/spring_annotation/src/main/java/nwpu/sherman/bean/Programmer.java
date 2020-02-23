package nwpu.sherman.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * 演示为Bean对象赋值
 *
 * @author sherman
 */
public class Programmer {
    @Value("${programmer.name}")
    private String name;
    @Value("${programmer.code}")
    private String code;
    @Value("${os.name}")
    private String osType;
    @Value("#{30-7}")
    private Integer age;

    public Programmer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", osType='" + osType + '\'' +
                ", age=" + age +
                '}';
    }
}
