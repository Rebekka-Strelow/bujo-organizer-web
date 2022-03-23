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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="bujo.web")
@PropertySource("classpath:persistance-mysql.properties")
@EnableTransactionManagement
public class BujoMainConfig {
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
	public DataSource securityDataSource() {
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
    public PlatformTransactionManager transactionManager() {
    	HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        return transactionManager;
    }
    
    @Bean 
    public SessionFactory sessionFactory() {
    	org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();
		  config.configure("hibernate.cfg.xml");
		  return config.buildSessionFactory();
    }
    
}









