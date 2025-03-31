package config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


@EnableTransactionManagement
@Configuration
@ComponentScan
public class AppConfig {
    private static final String PROPERTIES_OF_DB_CONNECTION_PATH = "src/main/resources/application.properties";

    @Bean
    public DataSource dataSource() {
        Properties properties = new Properties();
        DriverManagerDataSource dataSource = null;
        try (InputStream in = Files.newInputStream(Paths.get(PROPERTIES_OF_DB_CONNECTION_PATH))) {
            properties.load(in);
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl("url");
            dataSource.setUsername("username");
            dataSource.setPassword("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "validate");
        hibernateProperties.put("hibernate.show_sql", "true");

        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

}
