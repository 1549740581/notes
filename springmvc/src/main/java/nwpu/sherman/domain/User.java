package nwpu.sherman.domain;

/**
 * POJO映射测试，User对象各个属性自动映射，包括多级级联映射
 *
 * @author sherman
 */
public class User {
    private Integer id;
    private String username;
    private Address addr;

    public User(Integer id, String username, Address addr) {
        this.id = id;
        this.username = username;
        this.addr = addr;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddr() {
        return addr;
    }

    public void setAddr(Address addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", addr=" + addr +
                '}';
    }
}
