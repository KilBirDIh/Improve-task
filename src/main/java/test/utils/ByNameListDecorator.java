package test.utils;

import test.models.Meeting;

import java.util.List;
import java.util.ListIterator;

public class ByNameListDecorator extends ListDecorator
{
    private String name;
    public ByNameListDecorator(IListDecorator listDecorator, String name)
    {
        super(listDecorator);
        this.name = name;
    }

    @Override
    public List<Meeting> getList()
    {
        List<Meeting> result = super.getList();
        if(name != null && !name.equals(""))
        {
            ListIterator<Meeting> iterator = result.listIterator();
            while (iterator.hasNext())
            {
                if (!iterator.next().getSubject().contains(name))
                {
                    iterator.remove();
                }
            }
        }
        return result;
    }
}
