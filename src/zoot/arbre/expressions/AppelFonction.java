package zoot.arbre.expressions;

import zoot.tds.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

    /**
     * Getteur
     * @return le symbole
     */
    public Symbole getSymbole(){
        Symbole symbole = null;
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            if(et.getIdf().equals(idf) && et.estFonction()){
                symbole = TDS.getInstance().getTableDesSymboles().get(et) ;
            }
        }

        return symbole;
    }

    @Override
    public Type getType() {
        //On récupère le type de retour de la fonction
        return getSymbole().getType();
    }


    @Override
    public boolean estBool() {
        return false;
    }
}
