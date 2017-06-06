package test.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import test.dao.IOperations;
import test.dao.impl.DepartmentDao;
import test.dao.impl.EmployeeDao;
import test.dao.impl.MeetingDao;
import test.models.Department;
import test.models.Employee;
import test.models.Meeting;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("test")
public class DataConfig
{
    private Properties getHibernateProperties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/improve_group");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getSessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(new String[]{"test.models"});
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory)
    {
        return new HibernateTransactionManager(
                sessionFactory);
    }

    @Bean(name = "employeeDao")
    public IOperations<Employee> getEmployeeDao()
    {
        return new EmployeeDao();
    }

    @Bean(name = "departmentDao")
    public IOperations<Department> getDepartmentDao()
    {
        return new DepartmentDao();
    }

    @Bean(name = "meetingDao")
    public IOperations<Meeting> getMeetingDao()
    {
        return new MeetingDao();
    }

   /* @Bean(name = "employeeService")
    public IOperations<Employee> getEmployeeService()
    {
        return new EmployeeService();
    }

    @Bean(name = "departmentService")
    public IOperations<Department> getDepartmentService()
    {
        return new DepartmentService();
    }

    @Bean(name = "meetingService")
    public IOperations<Meeting> getMeetingService()
    {
        return new MeetingService();
    }*/
}

