package com.example.config;



import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 
 * @author intel
 *
 */
@Configuration
@Transactional
@EnableTransactionManagement
@ComponentScan({"com.example.domain"})
public class HibernateInitializator {

	public SessionFactory getSessionFactory() throws IOException {

        Properties hibernateProperties = getHibernateProperties();
        DataSource dataSource = getDatasourceConfiguration();
        LocalSessionFactoryBean localSessionFactoryBean = generateSessionFactoryBean(new String[] { "com.example.domain" },
            dataSource, hibernateProperties);
        SessionFactory sessionFactory = localSessionFactoryBean.getObject();

        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return sessionFactory;
    }
	private static LocalSessionFactoryBean generateSessionFactoryBean(String[] basePackage, DataSource dataSource,
	        Properties hibernateProperties) throws IOException {

	        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
	        localSessionFactoryBean.setDataSource(dataSource);
	        localSessionFactoryBean.setPackagesToScan(basePackage);
	        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
	        localSessionFactoryBean.afterPropertiesSet();
	        return localSessionFactoryBean;
	    }
	private static Properties getHibernateProperties() {

        Properties hibernateProperties = new Properties();
        
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.DerbyTenSevenDialect");
        hibernateProperties.put("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.generate_statistics", false);
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.put("hibernate.use_sql_comments", false);
        hibernateProperties.put("hibernate.connection.autoReconnect", true);
        hibernateProperties.put("hibernate.connection.autoReconnectForPools", true);
        /*spring.jpa.generate-ddl=true
        		spring.jpa.hibernate.ddl-auto=update
        		spring.jpa.database=default
        		spring.jpa.show-sql=true
		*/
        return hibernateProperties;
    }
	private DataSource getDatasourceConfiguration() {
		/*
		 jdbc.driverClassName=oracle.jdbc.OracleDriver  
		jdbc.url=jdbc:oracle:thin:@localhost:1521:XE
		jdbc.username=NAP
		jdbc.password=jun2017
		 */
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        dataSource.setUrl("jdbc:derby:test;create=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }
	@Bean
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
	        dataSource.setUrl("jdbc:derby:test;create=true");
	        dataSource.setUsername("root");
	        dataSource.setPassword("root");
	      return dataSource;
	   }

	 @Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "com.example.domain" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	   }
	 Properties additionalProperties() {
	       Properties properties = new Properties();
	       properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	       properties.setProperty(
	         "hibernate.dialect", "org.hibernate.dialect.DerbyTenSevenDialect");
	        
	       return properties;
	   }


}
