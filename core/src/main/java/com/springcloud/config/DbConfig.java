package com.springcloud.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.tool.schema.Action;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = DbConfig.DEFAULT_PACKAGE_NAME)
@ComponentScan(basePackages = { DbConfig.DEFAULT_PACKAGE_NAME })
@EntityScan(DbConfig.DEFAULT_PACKAGE_NAME)
@EnableJpaRepositories(basePackages = {
		DbConfig.DEFAULT_PACKAGE_NAME }, transactionManagerRef = DbConfig.TX_MANAGER, entityManagerFactoryRef = "localContainerEntityManagerFactoryBean")
@EnableTransactionManagement
@EnableConfigurationProperties({ JpaProperties.class })
public class DbConfig {

//	@Autowired
//	private JpaProperties jpaProperties;

	public static final String DEFAULT_PACKAGE_NAME = "com.springcloud";
	public static final String TX_MANAGER = "txManager";
	public static final String TX_MANAGER_FACTORY = "customentityManagerFactory";
	public static final String HIBERNATE_PHYSICAL_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy";
	public static final String HIBERNATE_IMPLICIT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy";
	public static final String SQL_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
	public static final String H2_DIALECT = "org.hibernate.dialect.H2Dialect";

	@Bean("localContainerEntityManagerFactoryBean")
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		Properties properties = new Properties();

		properties.put(org.hibernate.cfg.Environment.DIALECT, H2_DIALECT);
		properties.put(org.hibernate.cfg.Environment.URL, "jdbc:h2:mem:testdb");
		properties.put(org.hibernate.cfg.Environment.JPA_JDBC_USER, "sa");
		properties.put(org.hibernate.cfg.Environment.JPA_JDBC_PASSWORD, "");
		properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, Action.CREATE);
		properties.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
		properties.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
		properties.put(org.hibernate.cfg.Environment.DEFAULT_SCHEMA, "public");
		properties.put(AvailableSettings.IMPLICIT_NAMING_STRATEGY, HIBERNATE_IMPLICIT_NAMING_STRATEGY);
		properties.put(AvailableSettings.PHYSICAL_NAMING_STRATEGY, HIBERNATE_PHYSICAL_NAMING_STRATEGY);
		properties.put(AvailableSettings.JDBC_TIME_ZONE, "UTC");
		properties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true);

		localContainerEntityManagerFactoryBean.setJpaProperties(properties);
		localContainerEntityManagerFactoryBean.setPackagesToScan(DEFAULT_PACKAGE_NAME);
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("tenantdb-persistence-unit");

		return localContainerEntityManagerFactoryBean;
	}

	@Bean(TX_MANAGER_FACTORY)
	@Primary
	public EntityManagerFactory entityManagerFactory(
			@Qualifier("localContainerEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return entityManagerFactoryBean.getObject();
	}

	@Bean(TX_MANAGER)
	public PlatformTransactionManager transactionManager(
			@Qualifier(TX_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
