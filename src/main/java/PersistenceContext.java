import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Tanat on 6/16/2016.
 */
@Configuration
//for Spring data jpa classes
@EnableJpaRepositories("repositories")
@EnableTransactionManagement
public class PersistenceContext {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:xxxxxx");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        System.out.println("Datasource initialized");
        return dataSource;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        //search for entity classes
        entityManagerFactoryBean.setPackagesToScan("main.java.entities");

        Properties jpaProperties = new Properties();

        //Configures the used database dialect. This allows Hibernate to create SQL
        //that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto",
                "create-drop"
        );

        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy",
                "org.hibernate.cfg.ImprovedNamingStrategy"
        );
        jpaProperties.put("hibernate.enable_lazy_load_no_trans",
                "true"
        );
        //If the value of this property is true, Hibernate writes all SQL
        //statements to the console.
        jpaProperties.put("hibernate.show_sql",
                "true"
        );

        //If the value of this property is true, Hibernate will format the SQL
        //that is written to the console.
        jpaProperties.put("hibernate.format_sql",
                "true"
        );

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

/**
    github configure

 @Configuration
 @EnableTransactionManagement
 @ComponentScan("com.mycompany.myproject.persist")
 @EnableJpaRepositories("com.mycompany.myproject.persist")
 public class JPAConfig {

 @Bean(name = "dataSource")
 public DataSource dataSource() {
 return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).setName("myDb")
 .addScript("classpath:schema.sql").addScript("classpath:data.sql").build();
 }

 @Bean(name = "entityManagerFactory")
 public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
 LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
 factoryBean.setDataSource(dataSource());
 factoryBean.setPackagesToScan(new String[] { "com.mycompany.myproject.persist" });
 HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
 vendorAdapter.setShowSql(true);
 factoryBean.setJpaVendorAdapter(vendorAdapter);
 return factoryBean;
 }

 @Bean
 public PlatformTransactionManager transactionManager() {
 JpaTransactionManager transactionManager = new JpaTransactionManager();
 transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
 return transactionManager;
 }

 @Bean
 public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
 return new PersistenceExceptionTranslationPostProcessor();
 }

 @Bean
 public DozerBeanMapper getMapper() {
 return new DozerBeanMapper();
 }

 }
 **/