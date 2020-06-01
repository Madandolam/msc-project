package Cosc301;

import Cosc301.Container;

public interface PriorityQueue extends Container
{

    public abstract void enqueue(Comparable comparable);

    public abstract Comparable findMin();

    public abstract Comparable dequeueMin();

}