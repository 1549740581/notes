package nwpu.sherman.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 数据库事务相关配置
 * 1. 注入数据源
 * 2. 注入jdbcTemplate
 *
 * 3. 开启事务功能
 * 4. 注入平台事务管理器，管理数据源
 *
 * @author sherman
 */
@Configuration
@PropertySource("classpath:/db_property.properties")
@EnableTransactionManagement
@ComponentScan("nwpu.sherman")
public class TxConfig {
    @Value("${db.tx.user}")
    private String user;
    @Value("${db.tx.password}")
    private String password;
    @Value("${db.tx.driverClass}")
    private String driverClass;
    @Value("${db.tx.url}")
    private String url;

    @Bean("txDataSource")
    public DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(url);
        return dataSource;
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() throws PropertyVetoException {
        // 注意这里是从IOC容器中直接获取datasource，而不是多次创建
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate;
    }

    /**
     * 一定要有平台事务管理器来管理数据源，才能开启事务
     */
    @Bean("platformTransactionManager")
    public PlatformTransactionManager getPlatformTransactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(getDataSource());
    }
}
