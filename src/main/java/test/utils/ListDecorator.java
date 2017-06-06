package test.utils;

import test.models.Meeting;

import java.util.List;

public class ListDecorator implements IListDecorator
{
    private IListDecorator listDecorator;


    public ListDecorator(IListDecorator listDecorator)
    {
        this.listDecorator = listDecorator;
    }

    @Override
    public List<Meeting> getList()
    {
        return listDecorator.getList();
    }
}
