package nwpu.sherman.dao;

import org.springframework.stereotype.Repository;

/**
 * @author sherman
 */

/**
 * 自定义类PersonDao的名称为person-customer
 * 默认Bean的名称为类名首字母小写：【personDao】
 */
@Repository("personDao")
public class PersonDao {
    private int id;

    @Override
    public String toString() {
        return "PersonDao{" +
                "id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonDao(int id) {
        this.id = id;
    }

    public PersonDao() {
    }
}
