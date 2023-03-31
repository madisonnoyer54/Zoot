package zoot.arbre.expressions;

import zoot.tds.Type;

public class Non extends Expression{
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    protected Non(int n, int num) {
        super(n, num);
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public boolean estBool() {
        return false;
    }

    @Override
    public boolean estFonction() {
        return false;
    }

    @Override
    public String getIdf() {
        return null;
    }

    @Override
    public boolean estConstante() {
        return false;
    }
}
