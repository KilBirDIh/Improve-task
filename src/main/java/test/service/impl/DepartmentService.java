package test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import test.dao.IOperations;
import test.models.Department;
import test.service.common.AbstractHibernateService;

@Service
public class DepartmentService extends AbstractHibernateService<Department> implements IOperations<Department>
{

    @Autowired
    @Qualifier("departmentDao")
    private IOperations<Department> dao;

    @Autowired
    public IOperations<Department> getDao()
    {
        return dao;
    }
}
