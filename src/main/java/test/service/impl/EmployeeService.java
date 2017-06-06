package test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import test.dao.IOperations;
import test.models.Employee;
import test.service.common.AbstractHibernateService;

@Service
public class EmployeeService extends AbstractHibernateService<Employee> implements IOperations<Employee>
{
    @Autowired
    @Qualifier("employeeDao")
    private IOperations<Employee> dao;

    @Autowired
    public IOperations<Employee> getDao()
    {
        return dao;
    }
}
