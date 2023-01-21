package zoot.arbre.variable;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.expressions.Expression;

public abstract class Variable extends Expression {

    protected String variable;
    protected Variable(String texte, int n) {
        super(n);
        variable = texte;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

}
