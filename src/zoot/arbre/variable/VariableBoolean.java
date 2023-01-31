package zoot.arbre.variable;

import zoot.arbre.expressions.Variable;

public class VariableBoolean extends Variable {
    protected VariableBoolean(String texte,int n) {
        super(texte,n);
    }


    @Override
    public String toMIPS() {

        String varaibleBool = variable;
        return null;
    }
}
