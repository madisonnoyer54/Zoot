package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;
import zoot.tds.Type;

import java.util.List;

public class Non extends Unaire{
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    public Non(int n, int num,Expression e) {
        super(n, num,e);
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
