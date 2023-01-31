package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Affectation extends Instruction{
    protected Expression exp ;

    public Affectation(Expression e,int n) {
        super(n);
        exp = e;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
