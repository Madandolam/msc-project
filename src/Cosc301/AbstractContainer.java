package Cosc301;

public abstract class AbstractContainer implements Container, Comparable {
	protected int count;
   
        @Override
	public int getCount () {
		return count;
	}

        @Override
	public boolean isEmpty () {
		return getCount () == 0;
	}

        @Override
	public boolean isFull () {
		return false;
	}

        @Override
	public void accept(Visitor visitor) {
		Iterator iterator = iterator();

		while ( iterator.hasNext() && !visitor.isDone())
			visitor.visit(iterator.next());
	}

        @Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		AbstractVisitor visitor = new AbstractVisitor() {
			private boolean comma;
						
                        @Override
			public void visit(Object obj) {
				if(comma)
					buffer.append(" ");
				buffer.append(obj);
				comma = true;
                               
			}
		};

		accept(visitor);
		return "" + buffer;
	}
	
        @Override
	public int compareTo(Object object){
		throw new MethodNotImplemented();
	}
	
        @Override
	public abstract void purge();
        @Override
	public abstract Iterator iterator(); 
}
