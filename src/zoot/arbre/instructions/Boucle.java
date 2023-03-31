package zoot.arbre.instructions;

public class Boucle extends Instruction{
    /**
     * Constructeur
     *
     * @param n
     * @param numBloc
     */
    protected Boucle(int n, int numBloc) {
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
