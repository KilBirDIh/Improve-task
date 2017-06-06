package test.utils;

import test.models.Meeting;

import java.util.List;

public class MeetingList implements IListDecorator
{
    private List<Meeting> meetingList;

    public MeetingList(List<Meeting> meetingList)
    {
        this.meetingList = meetingList;
    }

    @Override
    public List getList()
    {
        return meetingList;
    }
}
