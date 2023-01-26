package config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import core.config.adaptor.MybatisConfigureAdaptor;
import core.util.PropertiesUtil;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Configuration
@EnableTransactionManagement
@MapperScan(
	basePackages= {"kr.co.**.*.mapper"},
	annotationClass=Mapper.class,
	sqlSessionFactoryRef="sqlSessionFactory"
)
public class DataSourceConfig extends MybatisConfigureAdaptor {
	private static Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

	@Override
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		log.debug("getProdMode ::: " + PropertiesUtil.getProdMode());

		String JndiYn = PropertiesUtil.getProperty("globals.jndi.useYn");
		String ProdMode = PropertiesUtil.getProdMode();

		if("Y".equals(JndiYn)) {
			if(log.isDebugEnabled()) log.debug("JNDI 사용");

			final JndiDataSourceLookup dslookup = new JndiDataSourceLookup();
			dslookup.setResourceRef(true);
			DataSource datsSource = dslookup.getDataSource(PropertiesUtil.getProperty("globals.jndi.name"));
			return datsSource;
		}else {
			if(log.isDebugEnabled()) log.debug("JNDI 미사용");

			return buildDataSource(
				PropertiesUtil.getProperty("globals.db.driverClassName")
					, PropertiesUtil.getProperty(ProdMode + ".db.url")
					, PropertiesUtil.getProperty(ProdMode + ".db.username")
					, PropertiesUtil.getProperty(ProdMode + ".db.password"));
		}
	}

	@Bean
	public DataSourceTransactionManager transactionManager(){
		return new DataSourceTransactionManager(dataSource());
	}

	@Override
	protected void setupSqlSessionFactoryBean(SqlSessionFactoryBean factoryBean) {
		try {
			factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sql/mariadb/**/*.xml"));
			factoryBean.setTypeAliasesPackage("kr.co.heartmedia.vo");
			Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:sql/setting/Configuration.xml");
			factoryBean.setConfigLocation( res[0] );
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() {
		return buildSessionFactory(true);
	}

	@Override
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate() {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	@Override
	protected void setupDataSource(BasicDataSource dataSource) {
		dataSource.setTestOnBorrow(true);
		dataSource.setValidationQuery("select 1 from dual");
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(7200000);
	}

}
