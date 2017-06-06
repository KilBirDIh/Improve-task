package test.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericHibernateDao<T extends Serializable> extends AbstractHibernateDao<T> implements IGenericDao<T>
{
    public GenericHibernateDao()
    {

    }

    public GenericHibernateDao(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
}
