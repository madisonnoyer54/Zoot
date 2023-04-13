package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.tds.Type;

import java.util.ArrayList;

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

    public abstract boolean estBoucleOuCondition();

    public abstract Boolean contientRetourner();

    public abstract Instruction getRetourner();
}
