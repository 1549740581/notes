package nwpu.sherman.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import nwpu.sherman.bean.AllColor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * 注册三个数据源，分别用于测试、开发和生产环境，注意三个数据源四个基本元素的注入方式
 * 主要是为了演示之前学习的内容，实际开发中统一使用一种即可
 *
 * 使用@Profile注解，根据当前环境的不同，启动不同的数据源，默认情况下执行@Profile("default")注解的数据源
 *
 * @author sherman
 */

@Configuration
@PropertySource("classpath:/db_property.properties")
public class DataSourceConfig implements EmbeddedValueResolverAware {
    @Value("${db.user}")
    private String user;
    private String driverClass;
    @Value("${db.url}")
    private String url;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.driverClass = resolver.resolveStringValue("${db.driverClass}");
    }

    /**
     * 测试数据源
     */
    @Bean("dataSourceTest")
    @Profile("test")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setPassword(pwd);
        return dataSource;
    }

    /**
     * 开发数据源
     */
    @Bean("dataSourceDev")
    //@Profile("dev")
    @Profile("default")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setPassword(pwd);
        return dataSource;
    }

    /**
     * 生产数据源
     */
    @Bean("dataSourceProd")
    @Profile("prod")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setPassword(pwd);
        return dataSource;
    }

    /**
     * 没有表示任何@Profile的Bean无论什么环境都会被激活
     */
    @Bean()
    public AllColor getAllColor() {
        return new AllColor();
    }
}
