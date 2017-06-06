package test.service.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
public abstract class AbstractHibernateDao<T extends Serializable>
{
    private Class<T> clazz;
    @Autowired
    SessionFactory sessionFactory;

    public final void setClazz(Class<T> clazzToSet)
    {
        this.clazz = clazzToSet;
    }

    @Transactional
    public T findOne(long id)
    {
        return (T) getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<T> findAll()
    {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Transactional
    public void create(T entity)
    {
        getCurrentSession().persist(entity);
    }

    @Transactional
    public void update(T entity)
    {
        getCurrentSession().merge(entity);
    }

    @Transactional
    public void delete(T entity)
    {
        getCurrentSession().delete(entity);
    }

    @Transactional
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
