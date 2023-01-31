package zoot.arbre.variable;

import zoot.arbre.expressions.Variable;

public class VariableEntiere extends Variable {
    protected VariableEntiere(String texte, int n) {
        super(texte, n);
    }


    @Override
    public String toMIPS() {
        String varaibleEntiere = variable;
        return null;
    }
}
