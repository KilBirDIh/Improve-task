package test.utils;

import test.models.Meeting;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class ByDepartmentListDecorator extends ListDecorator
{
    private Long id;

    public ByDepartmentListDecorator(IListDecorator listDecorator, Long id)
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
                if (!Objects.equals(iterator.next().getResponsibleDepartment().getId(), id)) iterator.remove();
            }
        }
        return result;
    }
}
