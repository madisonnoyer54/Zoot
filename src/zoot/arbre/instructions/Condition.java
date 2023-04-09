package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;

public class Condition extends Instruction{
    BlocDInstructions b1;
    BlocDInstructions b2;
    Expression e;
    /**
     * Constructeur
     *
     * @param n
     * @param numBloc
     */
    public Condition(int n, int numBloc, Expression e, ArbreAbstrait a, ArbreAbstrait a1) {
        super(n, numBloc);
        b1 = (BlocDInstructions) a;
        b2 = (BlocDInstructions) a1;
        this.e = e;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }

    @Override
    public boolean estRetourner() {
        return false;
    }
}
