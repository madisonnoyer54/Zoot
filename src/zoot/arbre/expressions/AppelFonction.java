package zoot.arbre.expressions;

import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AppelFonction extends Expression{
    private String idf;
    private int n;
    private ArrayList<Expression> listParam;


    /**
     * Constructeur
     * @param idf
     * @param n
     */
    public AppelFonction(String idf,int n, int num, ArrayList<Expression> list) {
        super(n,num);
        this.idf = idf;
        this.n=n;
        listParam = list;
        verifier();
    }

    /**
     * Fonction vérifier de AppelFonction
     */
    @Override
    public void verifier() {
        boolean result = false;

        // Test si l'appel correspond a une declaration de fonction
        HashMap<Entree,Symbole> list = TDS.getInstance().getlistFonction();
        for (Entree et : list.keySet()) {
            SymboleFonction s = (SymboleFonction) TDS.getInstance().identifier(et);
            if(et.getIdf().toString().equals(idf.toString()) && listParam.size() == s.getNb()){
                result = true;
            }
        }

        if(result == false){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne +" : L'appel de fonction " + this.toString() + " n'appartient à aucune déclaration"));

        }
        //à continuer : parcourir la liste verifier la fonction actuelle
        //verifier que ce qui est donné en parametre de l'appel est de meme type que les parametres declarés dans la fonction
        //+vérifier si aucun parametre donné mais requis (dans la décla)
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
    public Symbole getSymbole() {

        Symbole symbole = null;
        // 0 car la fonction est forcement déclarer dans le main
        HashMap<Entree,Symbole> list = TDS.getInstance().getBlocs().get(0);
        for (Entree et : list.keySet()) {
            if(et.getIdf().equals(idf) && et.estFonction()){
                symbole = list.get(et) ;
            }
        }


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
     * Getteur idf
     * @return
     */
    public String getIdf() {
        return idf;
    }

    /**
     * Fonction toString retourne l'identifiant
     *
     * @return
     */
    @Override
    public String toString() {
        String param = listParam.get(0).toString();
        for( int i=1; i<listParam.size(); i++ ){
            param = param + "," +  listParam.get(i).toString();
        }
        return idf + "("+param+")";
    }
}
