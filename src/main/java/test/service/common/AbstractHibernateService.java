package test.service.common;

import org.springframework.transaction.annotation.Transactional;
import test.dao.IOperations;

import java.io.Serializable;
import java.util.List;

@Transactional(value = "transactionManager")
public abstract class AbstractHibernateService<T extends Serializable> extends AbstractService<T> implements IOperations<T>
{

    @Override
    public T findOne(final long id) {
        return super.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return super.findAll();
    }

    @Override
    public void create(final T entity) {
        super.create(entity);
    }

    @Override
    public void update(final T entity) {
         super.update(entity);
    }

    @Override
    public void delete(final T entity) {
        super.delete(entity);
    }

    @Override
    public void deleteById(final long entityId) {
        super.deleteById(entityId);
    }

}
