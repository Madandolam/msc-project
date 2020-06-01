/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semantic.web;

import Cosc301.Iterator;
import Cosc301.MyLinkedList;

/**
 *
 * @author HAJJI
 */
public class CommonBehaviorSet {
    protected MyLinkedList cbs;
    
    public CommonBehaviorSet(){
        cbs = new MyLinkedList();
    }
    
    public MyLinkedList getCbs(){
        return cbs;
    }
    
    public void addCbs(Object si){
        cbs.append((SemanticItem)si);
    }
    
    public String getCbsString(){
        String output = "";
        Iterator it = cbs.iterator();
        int count = 0;
        while(it.hasNext()){
            if(count < 1){
                SemanticItem sem = (SemanticItem)it.next();
                output += "\t"+sem.toString(); 
            }
            else{
                SemanticItem sem = (SemanticItem)it.next();
                output += "\n \t\t"+sem.toString();
            }
            count++;
        }
        return output;
    }
    
    @Override
    public String toString(){
        return getCbsString();
    }
}
