package test.utils;

import test.models.Meeting;

import java.util.List;

public class ByDepartmentListDecorator extends ListDecorator
{
    IListDecorator listDecorator;
    private int id;

    public ByDepartmentListDecorator(IListDecorator listDecorator, int id)
    {
        super(listDecorator);
        this.id = id;
    }

    @Override
    public List<Meeting> getList()
    {
        List<Meeting> temp = super.getList();

        return super.getList();
    }
}
