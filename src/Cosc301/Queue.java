package Cosc301;

import Cosc301.Container;
import Cosc301.Container;

public interface Queue extends Container
{

    public abstract Object getHead();

    public abstract void enqueue(Object obj);

    public abstract Object dequeue();

}