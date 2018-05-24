package com.intrasoft.handson.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = "com.intrasoft",
	enableDefaultTransactions = false)
@EntityScan({"com.intrasoft"})
@Slf4j
public class DatabaseConfiguration {

//	/**
//	 * Data source data source.
//	 *
//	 * @return the data source
//	 */
//	@Bean
//	public DataSource dataSource() {
//
//		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//		return dsLookup.getDataSource("jdbc/accountantDS");
//	}

//	/**
//	 * Entity manager factory entity manager factory.
//	 *
//	 * @param dataSource the data source
//	 *
//	 * @return the entity manager factory
//	 */
//	@Bean
//	public EntityManagerFactory entityManagerFactory(final DataSource dataSource) {
//
//		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(false);
//		final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("eu.slg.accountant.base.domain.entity");
//		factory.setDataSource(dataSource);
//		factory.afterPropertiesSet();
//		return factory.getObject();
//	}

//	/**
//	 * Transaction manager platform transaction manager.
//	 *
//	 * @param dataSource the data source
//	 *
//	 * @return the platform transaction manager
//	 */
//	@Bean
//	public PlatformTransactionManager transactionManager(final DataSource dataSource) {
//
//		final JpaTransactionManager tm = new JpaTransactionManager();
//		tm.setEntityManagerFactory(entityManagerFactory(dataSource));
//		return tm;
//		//return new DataSourceTransactionManager(dataSource);
//	}
//
//	/**
//	 * Jdbc template named parameter jdbc template.
//	 *
//	 * @param dataSource the data source
//	 *
//	 * @return the named parameter jdbc template
//	 */
//	@Bean
//	public NamedParameterJdbcTemplate jdbcTemplate(final DataSource dataSource) {
//
//		return new NamedParameterJdbcTemplate(dataSource);
//	}
}