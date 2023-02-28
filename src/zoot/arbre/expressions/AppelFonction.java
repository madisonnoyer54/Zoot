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
        String code;

        code = "\t# Appel de la fonction"+ idf +

                "\n\t# Réserve la place pour le résultat de la fonction\n"+
                "\tadd $sp, $sp, -4\n"+

                // Empiler la valeur du paramètre (zoot 3)

                "\tjal "+idf+"\n"+

                // On depile et on met dans S7
                "\t # On depile\n"+
                "\t add $sp, $sp, 4 # depile\n"+
                "\t sw $s7, $sp\n";



        return code ;
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

    @Override
    public String toString() {
        return idf;
    }

    public String getIdf() {
        return idf;
    }


}
