package semantic.web;

import java.util.LinkedList;
import java.util.Set;

public class AssociationRule {
    private Set<String> ruleElementSet;
    private LinkedList antecedent;
    private LinkedList consequent;

    public AssociationRule(Set<String> ruleElementSet, LinkedList anteedend, LinkedList consequent) {
        this.ruleElementSet = ruleElementSet;
        this.antecedent = anteedend;
        this.consequent = consequent;
    }

    public Set<String> getRuleElementSet() {
        return ruleElementSet;
    }

    public LinkedList getAntecedent() {
        return antecedent;
    }

    public LinkedList getConsequent() {
        return consequent;
    }
}
