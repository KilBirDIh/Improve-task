package test.utils;

import test.models.Meeting;

import java.util.List;
import java.util.ListIterator;

public class ByDepartmentListDecorator extends ListDecorator
{
    private Integer id;

    public ByDepartmentListDecorator(IListDecorator listDecorator, Integer id)
    {
        super(listDecorator);
        this.id = id;
    }

    @Override
    public List<Meeting> getList()
    {
        List<Meeting> result = super.getList();
        if(id != 0)
        {
            ListIterator<Meeting> iterator = result.listIterator();
            while (iterator.hasNext())
            {
                if (iterator.next().getResponsibleDepartment().getId() != id) iterator.remove();
            }
        }
        return result;
    }
}
