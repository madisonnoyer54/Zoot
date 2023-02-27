package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.tds.Type;

public abstract class Instruction extends ArbreAbstrait {


    /**
     * Constructeur
     * @param n
     */
    protected Instruction(int n) {
        super(n);
    }

    public abstract boolean estRetourner();

    public Type getType() {
        return null;
    }
}
