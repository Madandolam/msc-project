package semantic.web;

import Cosc301.Iterator;
import Cosc301.MyLinkedList;

/**
 *
 * @author HAJJI
 */
public class SemanticItem extends MyLinkedList{
    protected MyLinkedList subjects;
    protected String predicate;
    protected String object;
    
    public SemanticItem(String predicate, String object){
        this.predicate = predicate;
        this.object = object;
        subjects = new MyLinkedList();
    }
    
    public SemanticItem(String predicate){

        this.predicate = predicate;
    }
    
    public void addTjSubject(String subject){

        subjects.append(subject);
    }
    
    public MyLinkedList getSubjects(){

        return subjects;
    }
    
    public String getPredicate(){

        return predicate;
    }
    
    public String getObject(){

        return object;
    }
    
    public String getSubjectsString(){
        Iterator it = subjects.iterator();
        String subjectList = "";
        int count = 0;
        
        while(it.hasNext()){
            if(count > 0)
                subjectList +=", "+it.next();
            else{
                subjectList += it.next();
                count++;
            }
        }
        
        return subjectList;
    }
    
    @Override
    public String toString(){

        return "{ " + getSubjectsString() +
                " }" + "  " + "( " + predicate+", " + object + " )";
    }
    
}