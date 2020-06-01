package semantic.web;

/**
 *
 * @author HAJJI
 */
public  class Tuple implements Comparable<Object>{
    protected String Subject;
    protected String Predicate;
    protected String Object;
    
    public Tuple(String subject, String predicate, String object){
        this.Subject = subject;
        this.Predicate = predicate;
        this.Object = object;
    }
    
    public String getSubject(){
        return Subject.trim();
    }
    
    public String getPredicate(){
        return Predicate.trim();
    }
    
    public String getObject(){
        return Object.trim();
    }
    
    public String toString(){
        return Subject + " " + Predicate + " " + Object;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
