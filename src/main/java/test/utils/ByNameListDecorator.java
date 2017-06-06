package test.utils;

public class ByNameListDecorator extends ListDecorator
{
    IListDecorator listDecorator;

    public ByNameListDecorator(IListDecorator listDecorator)
    {
        super(listDecorator);
    }
}
