package test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import test.dao.IOperations;
import test.models.Meeting;
import test.service.common.AbstractHibernateService;

@Service
public class MeetingService extends AbstractHibernateService<Meeting> implements IOperations<Meeting>
{
    @Autowired
    @Qualifier("meetingDao")
    private IOperations<Meeting> dao;

    @Autowired
    public IOperations<Meeting> getDao()
    {
        return dao;
    }
}
