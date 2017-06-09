package test.utils;

import test.models.Meeting;

import java.util.List;
import java.util.ListIterator;

public class ByEmployeeListDecorator extends ListDecorator
{
    private Long id;

    public ByEmployeeListDecorator(IListDecorator listDecorator, Long id)
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
                if (!iterator.next().getMembersOfMeeting().stream().anyMatch(employee1 -> employee1.getId().equals(id))) iterator.remove();
            }
        }
        return result;
    }
}
