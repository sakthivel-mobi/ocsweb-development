package com.mobi.ocs.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import com.mobi.ocs.controller.OrderController;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.mobi.ocs" })
@PropertySource("classpath:persistence-mysql.properties")
public class HibernateConfig {

	// set up variable to hold the properties
	@Autowired
	private Environment env;
	protected static Logger logger = Logger.getLogger(HibernateConfig.class);

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		try {
			sessionFactory.setDataSource(dataSource());
			sessionFactory.setPackagesToScan(new String[] { "com.mobi.ocs.entity" });
			sessionFactory.setHibernateProperties(hibernateProperties());

		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
		}
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {

		// create connection pool
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		// set the jdbc driver class
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));

			// log the connection props
			// just to make sure we are REALLY reading data from properties file

			logger.info(">>> jdbc.url=" + env.getProperty("jdbc.url"));
			logger.info(">>> jdbc.user=" + env.getProperty("jdbc.user"));

			// set database connection props

			dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			dataSource.setUser(env.getProperty("jdbc.user"));
			dataSource.setPassword(env.getProperty("jdbc.password"));

			// set connection pool props

			dataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
			dataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
			dataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
			dataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		} catch (Exception e) {
			logger.error("Stacktrace : ", e);
		}
		return dataSource;

	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;

	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	// need a helper method
	// read environment property and convert to int

	private int getIntProperty(String propName) {

		String propVal = env.getProperty(propName);
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}
}