package zoot.arbre.expressions;

import zoot.tds.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AppelFonction extends Expression{
    private String idf;
    private int n;
    private ArrayList<Expression> parametres;

    /**
     * Constructeur
     * @param idf
     * @param n
     */
    public AppelFonction(String idf,int n) {
        super(n);
        this.idf = idf;
        this.n=n;
        this.parametres = new ArrayList<>();
    }

    /**
     * Fonction vérifier de AppelFonction
     */
    @Override
    public void verifier() {

    }

    /**
     * Fonction toMips qui génère le code mips à chaque appel de fonction
     * @return String code mips
     */
    @Override
    public String toMIPS() {
        String code;

        code = "\t# Appel de la fonction "+ idf +

                "\n\t# Réserve la place pour le résultat de la fonction\n"+
                "\tadd $sp, $sp, -4\n"+

                // Empiler la valeur du paramètre
                "\tsw $a0, ($sp) # On empile la valeur du paramètre a0 sur la pile\n"+
                "\tadd $sp, $sp, -4 # On ajuste le pointeur de pile\n"+


                "\tjal "+idf+"\n"+

                // On depile et on met dans S7
                "\tadd $sp, $sp, 4 # Depile\n"+
                "\tsw $v0, 0($sp) # On met dans v0\n";



        return code ;
    }

    /**
     * Getteur
     * @return le symbole
     */
    public Symbole getSymbole(){

        Symbole symbole = null;
        /*
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            if(et.getIdf().equals(idf) && et.estFonction()){
                symbole = TDS.getInstance().getTableDesSymboles().get(et) ;
            }
        }

         */

        return symbole;
    }

    /**
     * Fonction qui retourne le type de retour de la fonction
     * @return
     */
    @Override
    public Type getType() {
        //On récupère le type de retour de la fonction
        return getSymbole().getType();
    }

    /**
     * Fonction estbool indique si expression est constantebool
     * @return
     */
    @Override
    public boolean estBool() {
        return false;
    }

    /**
     * Fonction estFonction indique si expression est fonction
     * @return
     */
    @Override
    public boolean estFonction() {
        return true;
    }
    /**
     * Fonction toString retourne l'identifiant
     * @return
     */
    @Override
    public String toString() {
        return idf;
    }

    /**
     * Getteur idf
     * @return
     */
    public String getIdf() {
        return idf;
    }


}
