package bujo.web.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="bujo.web")
@PropertySource({"classpath:persistance-mysql.properties", "classpath:security-persistance-mysql.properties"})
public class BujoMainConfig implements WebMvcConfigurer {
	@Autowired
	private Environment env; //liesst properties-file ein
	
	private Logger logger = Logger.getLogger(getClass().getName());
	

	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource(); //Setze C3P0 Data Source
		
		logger.info(">>> Starte Auslesen und Anwenden von Einstellungen aus persistance-mysql.properties");
		
		try { 
			//Setze JDBC Connection daten aus properties datei
			logger.info(">>> Starte JDBC Connection config");
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
			dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
			dataSource.setUser(env.getProperty("jdbc.user"));
			dataSource.setPassword(env.getProperty("jdbc.password"));
		
			logger.info(">>> Starte Connection Pool config");
			dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
			dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
			dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
			dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
		
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		logger.info(">>> persistance-mysql.properties erfolgreich angewandt.");
		
		logger.info(">>> Data Source erstellt");
		return dataSource;
	}
	
	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource(); //Setze C3P0 Data Source
		
		logger.info(">>> Starte Auslesen und Anwenden von Einstellungen aus security-persistance-mysql.properties");
		
		try { 
			//Setze JDBC Connection Daten aus properties datei
			logger.info(">>> Starte Security JDBC Connection config");
			dataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
			dataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
			dataSource.setUser(env.getProperty("security.jdbc.user"));
			dataSource.setPassword(env.getProperty("security.jdbc.password"));
		
			logger.info(">>> Starte Security Connection Pool config");
			dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.initialPoolSize")));
			dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.minPoolSize")));
			dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.maxPoolSize")));
			dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("security.connection.pool.maxIdleTime")));
		
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		logger.info(">>> security-persistance-mysql.properties erfolgreich angewandt.");
		
		logger.info(">>> Security Data Source erstellt");
		return dataSource;
	}
	
	private Properties getHibernateProperties() {
		//Setze Hibernate-properties
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return props;
	}
	
    @Bean 
    public LocalSessionFactoryBean sessionFactory() {
    	LocalSessionFactoryBean sessionFactory = new	LocalSessionFactoryBean();
    	sessionFactory.setDataSource(dataSource());
    	sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
    	sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    	logger.info(">>> Starte HibernateTransactionManager-Erstellung und config.");
	    HibernateTransactionManager txManager = new HibernateTransactionManager();
	    txManager.setSessionFactory(sessionFactory);
    	logger.info(">>> HibernateTransactionManager-Erstellung und config abgeschlossen.");
	    return txManager;
    }
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry
    		.addResourceHandler("/resources/**")
    		.addResourceLocations("/resources/");
    }
    
}









