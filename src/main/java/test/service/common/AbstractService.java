package test.service.common;

import test.dao.IOperations;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T extends Serializable> implements IOperations<T>
{
    @Override
    public T findOne(final long id) {
        return getDao().findOne(id);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public void create(final T entity) {
        getDao().create(entity);
    }

    @Override
    public void update(final T entity) {
        getDao().update(entity);
    }

    @Override
    public void delete(final T entity) {
        getDao().delete(entity);
    }

    @Override
    public void deleteById(final long entityId) {
        getDao().deleteById(entityId);
    }

    protected abstract IOperations<T> getDao();
}
