package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;

public abstract class Binaire extends Expression {
    protected Expression e1;
    protected Expression e2;
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    protected Binaire(int n, int num, Expression e1, Expression e2) {
        super(n, num);
        this.e1 = e1;
        this.e2 = e2;
    }

    public abstract void verifier();

    public abstract String toString();


}
