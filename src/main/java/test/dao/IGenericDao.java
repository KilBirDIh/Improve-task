package test.dao;

import java.io.Serializable;

public interface IGenericDao<T extends Serializable> extends IOperations<T>
{
}
