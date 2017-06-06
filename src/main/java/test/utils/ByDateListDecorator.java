package test.utils;

import test.models.Meeting;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ListIterator;

public class ByDateListDecorator extends ListDecorator
{
    LocalDateTime startDate;
    LocalDateTime endDate;

    public ByDateListDecorator(IListDecorator listDecorator, LocalDateTime startDate, LocalDateTime endDate)
    {
        super(listDecorator);
        this.startDate = startDate;
        this.endDate = endDate;

    }

    @Override
    public List<Meeting> getList()
    {
        List<Meeting> temp = super.getList();
        if (endDate == null && startDate == null)
        {
            return temp;
        } else if (endDate == null)
        {
            ListIterator<Meeting> iterator = temp.listIterator();
            while (iterator.hasNext())
            {
                if (iterator.next().getMeetingDateTime().isBefore(startDate))
                    iterator.remove();
            }
        } else if (startDate == null)
        {
            ListIterator<Meeting> iterator = temp.listIterator();
            while (iterator.hasNext())
            {
                if (iterator.next().getMeetingDateTime().isAfter(endDate))
                    iterator.remove();
            }
        } else
        {
            ListIterator<Meeting> iterator = temp.listIterator();
            while (iterator.hasNext())
            {
                if (iterator.next().getMeetingDateTime().isAfter(endDate) && iterator.next().getMeetingDateTime().isBefore(startDate))
                    iterator.remove();
            }
        }
        return temp;
    }
}
