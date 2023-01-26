package core.config.mybatis;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import core.exception.HMException;


/**
 * 
 * @author ysKo
 * MybatisConfigAdaptor 로 교체함
 */
@Deprecated
@Configuration
public abstract class MybatisConfigBase {
	abstract protected String getDatabaseName();
	
	private static Logger log = LoggerFactory.getLogger(MybatisConfigBase.class);
	
    abstract protected String[] getMappedPackageNames();

    protected Properties mybatisProperties(){
		Properties props = new Properties();

		props.put("cacheEnabled", true);
		props.put("lazyLoadingEnabled", false);
		props.put("multipleResultSetsEnabled", true);
		props.put("useColumnLabel", true);
		props.put("useGeneratedKeys", false);
		props.put("defaultExecutorType", "SIMPLE");
		props.put("defaultStatementTimeout", 25000);

		return props;
	}

	protected DataSource buildDataSource(String driverClass, String url, String username, String password){
		BasicDataSource ds = new  BasicDataSource();

		ds.setDriverClassName(driverClass);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		
		setupDataSource(ds);

		return ds;
	}

	@Bean(destroyMethod = "close")
    abstract public DataSource dataSource();
	
	/**
	 * DataSouce 설정을 추가할 수 있다.
	 */
	protected void setupDataSource(BasicDataSource dataSource) { /** TODO */ }

	/**
     * factoryBean 에 추가 설정을 지정할 수 있습니다.
     */
    protected void setupSessionFactory(SqlSessionFactoryBean factoryBean) { /** TODO */ }

    @Bean
    public SqlSessionFactory sessionFactory() {
    	if (log.isInfoEnabled())
            log.debug("SessionFactory Bean을 생성합니다...");

    	SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

    	// Drived class에서 추가 작업을 수행할 수 있도록 합니다.
        setupSessionFactory(factoryBean);
        
        factoryBean.setConfigurationProperties(mybatisProperties());
        factoryBean.setDataSource(dataSource());

        

        try {
            factoryBean.afterPropertiesSet();

            if (log.isInfoEnabled())
                log.debug("SessionFactory Bean을 생성했습니다!!!");

            return factoryBean.getObject();

        } catch (NullPointerException ne) {
			log.error(ne.getMessage());
			throw new HMException(ne);
		} catch (Exception e) {
            throw new RuntimeException("SessionFactory 빌드에 실패했습니다.", e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSession(){
    	return new SqlSessionTemplate(sessionFactory());
    }

//    protected void setupMapperConfigure(MapperScannerConfigurer mapperScannerConfigurer){}

//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer(){
//    	MapperScannerConfigurer configure = new MapperScannerConfigurer();
//
//    	setupMapperConfigure(configure);
//
//    	return configure;
//    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
    	log.debug("Transcation Manager Start!");
        return new DataSourceTransactionManager(dataSource());
    }
}
