package nwpu.sherman.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * dao层，完成向tbl_user中插入一条记录
 *
 * @author sherman
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(String username, int age) {
        String sql = "insert into `tbl_user`(username, age) values(?, ?)";
        jdbcTemplate.update(sql, username, age);
    }
}
