package semantic.web;

import Cosc301.Iterator;
import Cosc301.MyLinkedList;

import java.io.File;
import java.util.Scanner;
/**
 *
 * @author HAJJI
 */

public class SemanticWeb {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String fileLocation = "c://semantic//data.txt";
        String completeText = "";
        File file = null;
        Scanner input = null;
        
        try {
             file = new File(fileLocation);
             input = new Scanner(file);
          
        } catch (Exception ex) {
            ex.getMessage();
        }
        
        
        MyLinkedList triples = new MyLinkedList();
       
        while(input.hasNext()){
            //Split each line into Subject, Predicate, Object
            String[] triple = input.nextLine().split(",");
            //Populate the table here
            triples.append(new Tuple(triple[0], triple[1], triple[2]));
        }
               
        //START THE WORK NOW
        MyLinkedList sis = SI(triples);
        System.out.println("\n\t SEMANTIC ITEMS");
        System.out.println("\t --------------");
        Iterator it = sis.iterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
        
       
        System.out.println("\n TOTAL COMMON BEHAVIOUR \n ----------------------\n");
        int count = 1;
        double SimTh = 0.5;
        Iterator tcbs = TCBS(sis, SimTh).iterator();
        while(tcbs.hasNext()){
            System.out.print("cbs" + count);
            System.out.println(tcbs.next().toString());
           // String[] strtcbs = tcbs.next().toString().split("  ");
           // System.out.println(strtcbs[strtcbs.length-1]);
            System.out.println("\n");
            count++;
        }


        System.out.println("\n SEMANTIC ASSOCIATION RULES \n  -------------------------\n");

       
    }



    
    // TOTAL COMMON BEHAVIOR
    public static MyLinkedList TCBS(MyLinkedList sis, double SimTh){
        MyLinkedList TCBS = new MyLinkedList();
        CommonBehaviorSet cbs = null;
        boolean foundFlag = false;
        
        Iterator sisIt = sis.iterator();
       
        //3 for each sii e SI do
        while (sisIt.hasNext()) {
            
            SemanticItem si = (SemanticItem)sisIt.next();
            
            Iterator tcbsIt = TCBS.iterator();
            
            while (tcbsIt.hasNext()) {

                //Object cbsiobj = tcbsIt.next();
                CommonBehaviorSet cbsi = (CommonBehaviorSet)tcbsIt.next();
                MyLinkedList setb = unionWithCbs(si, cbsi);
                MyLinkedList seta = intersectionWithCbs(si, cbsi);
               
                int numerator = setCardinality(intersectionWithList(si.subjects, seta));
                int denominator = setCardinality(unionWithList(si.subjects, setb));
                double SD = numerator/denominator;
                //System.out.println(SD);
                if(SD >= SimTh){                    
                    cbs.addCbs(si);
                    foundFlag = true;
                }

                
            }
           
            if (!foundFlag) {
                   
                cbs = new CommonBehaviorSet();
                cbs.addCbs(si);
                TCBS.append(cbs);
            }
            foundFlag = false;
            
        }

        return TCBS;
    }
    
    public static MyLinkedList intersectionWithList(MyLinkedList si, MyLinkedList listobj){
        MyLinkedList intersect = new MyLinkedList();
        
        boolean foundFlag = false;
        Iterator semA = si.iterator();
        while (semA.hasNext()) {

            Object sema = semA.next();
            String a = (String) sema;

            Iterator semB = listobj.iterator();
            while (semB.hasNext()) {
                Object semb = semB.next();
                String b = (String) semb;
                if (a.equalsIgnoreCase(b)) {
                    foundFlag = true;
                    break;
                }

            }

            if (foundFlag) {
                intersect.append(sema);
            }

            foundFlag = false;
        }

        return intersect;
    }
    
    
    public static MyLinkedList unionWithList(MyLinkedList si, MyLinkedList listobj){
        MyLinkedList unionset = new MyLinkedList();

        unionset.assign(si);

        boolean foundFlag = false;
        Iterator semB = listobj.iterator();
        while (semB.hasNext()) {
            Object sem = semB.next();
            String b = (String) sem;
            Iterator semA = si.iterator();
            while (semA.hasNext()) {
                String a = (String) semA.next();
                if (a.equalsIgnoreCase(b)) {
                    // System.out.println(a+" => "+b);
                    foundFlag = true;
                    break;
                }

            }
            if (!foundFlag) {
                unionset.append(b);
                foundFlag = false;
            }

        }
        
        return unionset;
    }
    
    public static MyLinkedList unionWithCbs(SemanticItem si, CommonBehaviorSet cbsobj){
        MyLinkedList union = new MyLinkedList();
        union.append(si);
        CommonBehaviorSet cbs = cbsobj;
        Iterator it = cbs.cbs.iterator();
        while(it.hasNext()){
           
            SemanticItem s = (SemanticItem)si;
            MyLinkedList result = union(s, (SemanticItem)it.next());
            union.assign(result);
        }
        
        return union;
    }
    
    public static MyLinkedList union(SemanticItem sia, SemanticItem sib){
       MyLinkedList unionset = new MyLinkedList();
       unionset.assign(sia);
        
        boolean foundFlag = false;
        Iterator semB = sib.subjects.iterator();
        while(semB.hasNext()){
            Object sem = semB.next();
            String b = (String)sem;
            MyLinkedList semA = sia.subjects;
            Iterator semIt = semA.iterator();
            while(semIt.hasNext()){
                String a = (String)semIt.next();
                
                if(a.equalsIgnoreCase(b)){
                   // System.out.println(a+" => "+b);
                    foundFlag = true;
                    break;
                }
                
               }
                if(!foundFlag){
                   unionset.append(sem);
                   foundFlag = false;
               }
          
        }
        
        return unionset;
    }
    
     public static MyLinkedList intersectionWithCbs(SemanticItem si, CommonBehaviorSet cbsobj){
        MyLinkedList intersect = new MyLinkedList();
        intersect.append(si);
        CommonBehaviorSet cbs = cbsobj;
        Iterator it = cbs.cbs.iterator();
        while(it.hasNext()){
            MyLinkedList result = intersection(si, (SemanticItem)it.next());
            intersect.assign(result);
        }
        
        return intersect;
     }
    
     public static MyLinkedList intersection(SemanticItem sia, SemanticItem sib){
        MyLinkedList intersect = new MyLinkedList();
        
        boolean foundFlag = false;
        Iterator semA = sia.subjects.iterator();
        while(semA.hasNext()){
            
            Object sem = semA.next();
            String a = (String)sem;
           
            Iterator semB = sib.subjects.iterator();
            while(semB.hasNext()){
                Object semb = semB.next();
                String b= (String)semb;
                if(a.equalsIgnoreCase(b)){
                    foundFlag = true;
                }
                
                if(foundFlag){
                intersect.append(sem);
                }
                
                foundFlag = false;   
            }
        }
        
        return intersect;
    }
    
    //////////////////////////////////////////////
    // DISPLAY THE CONTAINER                   ///
    //////////////////////////////////////////////
     public static String getLinkedListString(MyLinkedList list){
        Iterator it = list.iterator();
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
     
   
    //  COUNT THE ELEMENTS IN A LINKEDLIST
    public static int setCardinality(Object setobj){
         
        MyLinkedList set = (MyLinkedList)setobj;
        Iterator it = set.iterator();
        int count = 0;
        while(it.hasNext()){
            count++;
            it.next();
        }
        
        return count;
    }
     
    // SEMANTIC ITEMS
    public static MyLinkedList SI(MyLinkedList triples){
       // input : Triples
       // output: SI
        
       //SI <- 0; 
       // An empty list of SEMANTIC ITEMS
       MyLinkedList SI = new MyLinkedList();
       
       //foundFlag   false;
       Boolean foundFlag = false;
       
       Iterator triple = triples.iterator();
       //for each triple tj 2 Triples do
       while(triple.hasNext()){
           //4 pa0   the Pair of tj
           Object tuple = triple.next();
           //CAST OBJECT TO TUPLE TYPE
           Tuple pa = (Tuple) tuple;

            Iterator sii = SI.iterator();   
            //5 for each sii 2 SI do
            while(sii.hasNext()){
                
                //6 if pa0 = sii. pa then
                // CAST THE OBJECT FROM SI TO TUPLE
                SemanticItem si = (SemanticItem) sii.next();

                if((pa.getPredicate().equalsIgnoreCase(si.getPredicate()))
                        && (pa.getObject().equalsIgnoreCase(si.getObject()))){

                    //7 add the subject of tj to sii.es;
                    si.addTjSubject(pa.getSubject());
                    //8 foundFlag   true;
                    foundFlag = true;
                }
    
            }  
            // 9 if foundFlag = false then
            if (!foundFlag) {
                // 10 new Semantic Item si0i;
                SemanticItem si = new SemanticItem(pa.getPredicate(), pa.getObject());
                // 11 si0i   tj ;
                si.addTjSubject(pa.getSubject());
                // 12 add si0i to SI;
                SI.append(si);
               
            }
            foundFlag = false;    
       }
       
       Iterator si = SI.iterator();
       
       MyLinkedList newSi = new MyLinkedList();
       
        while (si.hasNext()) {
            SemanticItem sem = (SemanticItem)si.next();
            if(count(sem.getSubjects()) > 1)
                newSi.append(sem);
        }
       return newSi;
    }
public static int count(MyLinkedList list){
    int count = 0;
    
       Iterator siit = list.iterator();
        while (siit.hasNext()) {
           count++;
           siit.next();
            }
        
    return count;
}

}
