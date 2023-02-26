package zoot.arbre.expressions;

import zoot.tds.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AppelFonction extends Expression{
    private String idf;
    private int n;
    public AppelFonction(String idf,int n) {
        super(n);
        this.idf = idf;
        this.n=n;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();

        /*

        sb.append("    #Appel de fonction\n");
        sb.append("    #On alloue la valeur de retour\n");
        sb.append("    add $sp, $sp, -4\n\n");
        sb.append("    #Jump" + idf + "\n");
        sb.append("    jal " + label + "\n\n");
        sb.append("    #on depile dans $v0\n" );
        sb.append("    add $sp, $sp, 4\n");
        sb.append("    lw $v0, 0($sp)\n\n");

         */


        return sb.toString();
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

    @Override
    public boolean estFonction() {
        return true;
    }

    public String getIdf() {
        return idf;
    }


}
