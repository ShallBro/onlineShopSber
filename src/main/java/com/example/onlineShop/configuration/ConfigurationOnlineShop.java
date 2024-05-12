package com.example.onlineShop.configuration;


import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Конфигурационный класс для настройки приложения интернет-магазина.
 * Этот класс настраивает компоненты Spring, такие как источник данных, фабрику сессий
 * и управление транзакциями с использованием Hibernate.
 */
@Configuration
@ComponentScan("com.example.onlineShop")
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
public class ConfigurationOnlineShop {

  private final Environment env;

  /**
   * Конструктор для класса ConfigurationOnlineShop.
   *
   * @param env окружение, используемое для доступа к свойствам
   */
  @Autowired
  public ConfigurationOnlineShop(Environment env) {
    this.env = env;
  }

  /**
   * Настраивает свойства Hibernate.
   *
   * @return свойства Hibernate
   */
  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
    return properties;
  }

  /**
   * Настраивает источник данных для Hibernate.
   *
   * @return источник данных
   */
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getRequiredProperty("hibernate.driver_class"));
    dataSource.setUrl(env.getRequiredProperty("hibernate.connection.url"));
    dataSource.setUsername(env.getRequiredProperty("hibernate.connection.username"));
    dataSource.setPassword(env.getRequiredProperty("hibernate.connection.password"));
    return dataSource;
  }

  /**
   * Настраивает фабрику сессий для Hibernate.
   *
   * @return бин фабрики сессий
   */
  @Bean(name = "entityManagerFactory")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("com.example.onlineShop.entity");
    sessionFactory.setHibernateProperties(hibernateProperties());
    return sessionFactory;
  }

  /**
   * Настраивает менеджер транзакций для Hibernate.
   *
   * @return менеджер транзакций Hibernate
   */
  @Bean
  public PlatformTransactionManager hibernateTransactionalManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactory().getObject());
    return transactionManager;
  }
}

