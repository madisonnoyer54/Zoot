package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;

public class Affectation extends Instruction{
    protected Expression exp ;
    protected Idf variable;

    public Affectation(String idf,Expression e,int n) {
        super(n);
        variable = new Idf(idf,n);
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
