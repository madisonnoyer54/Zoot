package zoot.arbre.variable;

import zoot.arbre.variable.Variable;

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
