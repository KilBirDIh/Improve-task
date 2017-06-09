package test.dao.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import test.dao.IOperations;

import java.io.Serializable;
import java.util.List;

@Repository
public abstract class AbstractHibernateDao<T extends Serializable> extends AbstractDao<T> implements IOperations<T>
{
    @Autowired
    SessionFactory sessionFactory;

    public T findOne(long id)
    {
        return (T) getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll()
    {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public void create(T entity)
    {
        getCurrentSession().saveOrUpdate(entity);
    }


    public void update(T entity)
    {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(T entity)
    {
        getCurrentSession().delete(entity);
    }

    public void deleteById(long entityId)
    {
        T entity = findOne(entityId);
        delete(entity);
    }

    private Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

}
