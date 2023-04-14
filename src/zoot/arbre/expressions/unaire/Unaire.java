package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;

public abstract class Unaire extends Expression {
    protected Expression e;
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    protected Unaire(int n, int num, Expression e) {
        super(n, num);
        this.e =e;
    }

    @Override
    public boolean estBinaire() {
        return false;
    }

}
