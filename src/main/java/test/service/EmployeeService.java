package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.models.Employee;
import test.dao.IGenericDao;

@Service
public class EmployeeService
{
    private IGenericDao<Employee> dao;

    @Autowired
    public void setDao(IGenericDao<Employee> dao)
    {
        this.dao = dao;
        this.dao.setClazz(Employee.class);
    }
}
