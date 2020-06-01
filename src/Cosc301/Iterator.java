package Cosc301;

import java.util.NoSuchElementException;

public interface Iterator {
	boolean hasNext ();
	Object next () throws NoSuchElementException;
}