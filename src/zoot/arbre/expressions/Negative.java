package zoot.arbre.expressions;

import zoot.tds.Type;

import java.util.List;

public class Negative extends Expression{
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    protected Negative(int n, int num) {
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

    @Override
    public int getNombreDePlaces() {
        return 0;
    }

    @Override
    public String toMips(List<String> registres) {
        return null;
    }
}
