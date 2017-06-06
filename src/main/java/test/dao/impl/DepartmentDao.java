package test.dao.impl;

import org.springframework.stereotype.Repository;
import test.dao.IOperations;
import test.dao.common.AbstractHibernateDao;
import test.models.Department;

@Repository
public class DepartmentDao extends AbstractHibernateDao<Department> implements IOperations<Department>
{
    public DepartmentDao()
    {
        super();

        setClazz(Department.class);
    }
}
