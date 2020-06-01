package Cosc301;

import Cosc301.AbstractVisitor;
import Cosc301.AbstractVisitor;

public class PrintingVisitor extends AbstractVisitor {
    int count =0 ;
        @Override
	public void visit(Object object) {
            count++;
                    System.out.print(object + "  ");
	}
        
        public int getCount(){
            return count;
        }
}
				