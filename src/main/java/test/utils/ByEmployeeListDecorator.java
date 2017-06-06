package test.utils;

public class ByEmployeeListDecorator extends ListDecorator
{
    IListDecorator listDecorator;

    public ByEmployeeListDecorator(IListDecorator listDecorator)
    {
        super(listDecorator);
    }
}
