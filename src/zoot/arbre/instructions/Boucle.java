package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;

public class Boucle extends Instruction{
    private BlocDInstructions blocDInstructions;
    private Expression e;
    /**
     * Constructeur
     *
     * @param n
     * @param numBloc
     */
    public Boucle(int n, int numBloc, ArbreAbstrait a, Expression e) {
        super(n, numBloc);
        this.e = e;
        blocDInstructions = (BlocDInstructions) a;

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
