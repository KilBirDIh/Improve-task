package test.dao.impl;

import org.springframework.stereotype.Repository;
import test.dao.IOperations;
import test.dao.common.AbstractHibernateDao;
import test.models.Employee;

@Repository
public class EmployeeDao extends AbstractHibernateDao<Employee> implements IOperations<Employee>
{

    public EmployeeDao()
    {
        super();

        setClazz(Employee.class);
    }
}
