package Cosc301;

import Cosc301.Container;
import Cosc301.Container;

public interface Stack extends Container
{

    public abstract Object getTop();

    public abstract void push(Object obj);

    public abstract Object pop();
}