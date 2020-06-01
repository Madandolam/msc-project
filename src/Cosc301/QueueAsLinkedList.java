package Cosc301;

import Cosc301.ContainerEmptyException;
import Cosc301.AbstractContainer;
import Cosc301.AbstractContainer;
import Cosc301.ContainerEmptyException;
import java.util.NoSuchElementException;

public class QueueAsLinkedList extends AbstractContainer implements Queue
{
	protected MyLinkedList list;
	
    public QueueAsLinkedList()
    {
        list = new MyLinkedList();
    }

    public void purge()
    {
        list.purge();
        count = 0;
    }

    public Object getHead()
    {
        if(count == 0)
            throw new ContainerEmptyException();
        else
            return list.getFirst();
    }

    public void enqueue(Object obj)
    {
        list.append(obj);
        count++;
    }

    public Object dequeue()
    {
        if(count == 0)
            throw new ContainerEmptyException();
        else
        {
            Object obj = list.getFirst();
            list.extractFirst();
            count--;
            return obj;
        }
    }

    public Iterator iterator()
    {
        return new Iterator() {
        	
        	MyLinkedList.Element position = list.getHead();

            public boolean hasNext()
            {
                return position != null;
            }

            public Object next()
            {
                if(position == null)
                    throw new NoSuchElementException();
                else
                {
                    Object obj = position.getData();
                    position = position.getNext();
                    return obj;
                }
            }
            
        };
    }
}