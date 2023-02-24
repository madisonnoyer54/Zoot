package zoot.arbre.expressions;

import zoot.tds.Entree;
import zoot.tds.EntreeFonction;
import zoot.tds.TDS;
import zoot.tds.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class AppelFonction extends Expression{
    private String idf;
    private String type;
    private int n;
    public AppelFonction(int n,String idf) {
        super(n);
        this.n=n;
        this.idf = idf;
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
        //On récupère le type de retour de la fonction
        return TDS.getInstance().identifier(new EntreeFonction(this.idf,this.n)).getType();
    }

    @Override
    public boolean estBool() {
        return false;
    }
}
