package test.dao.impl;

import org.springframework.stereotype.Repository;
import test.dao.IOperations;
import test.dao.common.AbstractHibernateDao;
import test.models.Meeting;

@Repository
public class MeetingDao extends AbstractHibernateDao<Meeting> implements IOperations<Meeting>
{
    public MeetingDao()
    {
        super();

        setClazz(Meeting.class);
    }
}
