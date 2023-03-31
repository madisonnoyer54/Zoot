package zoot.arbre.operations;

import zoot.arbre.ArbreAbstrait;

public abstract class Operation extends ArbreAbstrait {
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    protected Operation(int n, int num) {
        super(n, num);
    }
}
