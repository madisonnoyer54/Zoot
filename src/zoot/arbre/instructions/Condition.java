package zoot.arbre.instructions;

public class Condition extends Instruction{
    /**
     * Constructeur
     *
     * @param n
     * @param numBloc
     */
    protected Condition(int n, int numBloc) {
        super(n, numBloc);
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
