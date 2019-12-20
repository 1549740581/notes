package nwpu.sherman.service;

import nwpu.sherman.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 向表中tbl_user插入一条记录的service
 *
 * @author sherman
 */

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional(value = "platformTransactionManager", rollbackFor = Exception.class)
    public void insert(String username, int age) {
        userDao.insert(username, age);
        /**
         * 模拟插入过程中会抛出异常，如果并不支持事务，则不会回滚，仍然插入成功
         */
        System.out.println(10 / 0);
        System.out.println("插入完成......");
    }
}
