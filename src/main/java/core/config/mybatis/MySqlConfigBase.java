package core.config.mybatis;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author ysKo
 * MybatisConfigAdaptor 로 교체함
 */
@Deprecated
@Configuration
@EnableTransactionManagement
public abstract class MySqlConfigBase extends MybatisConfigBase {

	@Override
    @Bean
    public Properties mybatisProperties() {
        Properties props = super.mybatisProperties();

        return props;
    }
	
	@Override
	protected void setupDataSource(BasicDataSource dataSource) {
		dataSource.setTestOnBorrow(true);
		dataSource.setValidationQuery("select 1");
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(7200000);
	}

}
