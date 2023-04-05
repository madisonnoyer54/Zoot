package zoot.arbre.expressions.Binaire;

import zoot.tds.Type;

import java.util.List;

public class Egale extends Binaire {
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    protected Egale(int n, int num) {
        super(n, num);
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

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
