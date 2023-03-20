package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.tds.Type;

public abstract class Instruction extends ArbreAbstrait {


    /**
     * Constructeur
     * @param n
     */
    protected Instruction(int n, int numBloc) {
        super(n,numBloc);
    }

    public abstract boolean estRetourner();

    public Type getType() {
        return null;
    }
}
