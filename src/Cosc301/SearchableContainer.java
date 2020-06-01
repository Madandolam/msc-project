package Cosc301;

import Cosc301.Container;
import Cosc301.Container;

public interface SearchableContainer extends Container {
	boolean isMember (Comparable object);
	void insert (Comparable object);
	void withdraw (Comparable obj);
	Comparable find (Comparable object);
}