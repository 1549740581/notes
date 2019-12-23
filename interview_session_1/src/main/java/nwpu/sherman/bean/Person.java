package nwpu.sherman.bean;

import org.springframework.stereotype.Component;

/**
 * 演示Bean的作用域
 *
 * @author sherman
 */
@Component
public class Person {
    private Integer id;
    private String name;

    public Person() {
        System.out.println("Person被创建了");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
