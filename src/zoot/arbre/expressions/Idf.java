package zoot.arbre.expressions;


import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;
import zoot.tds.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Idf extends Expression {

    protected String variable;
    private int noBloc;


    /**
     * Constructeur
     * @param texte
     * @param n
     */
    public Idf(String texte, int n, int num) {
        super(n,num);
        variable = texte;
        verifier();
        this.noBloc=num;


    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
       //Symbole symbole = TDS.getInstance().identifier(new Entree(variable, noLigne));

    }


    /**
     * Fonction toMips, traduction en mips
     * @return le mips en string
     */
    @Override
    public String toMIPS() {
        if(noBloc==0) {//TODO:à tester
            int deplacement = TDS.getInstance().getCompteurDeplace();
            int deplacementTotal = deplacement - getSymbole().getDeplacement();
            return "\tlw $v0," + deplacementTotal + "($s3)\n";
        }
        else{//au niveau de fonction
            int deplacement = TDS.getInstance().getCompteurDeplace();
            int deplacementParam = deplacement - getSymbole().getDeplacement();
            return "\tlw $v0," + deplacementParam + "($s7)\n";
        }
    }


    /**
     * Getteur
     * @return le symbole
     */
    public Symbole getSymbole(){
        Symbole symbole = null;

        // On regarde deja dans le bloc de fonction
        HashMap<Entree,Symbole> list = TDS.getInstance().getBlocs().get(numBloc);
        for (Entree et : list.keySet()) {
            if(et.getIdf().equals(variable) && !et.estFonction()){
                symbole = list.get(et) ;
            }
        }
        // Puis dans le main
        if(symbole == null){

            HashMap<Entree,Symbole> list2 = TDS.getInstance().getBlocs().get(0);
            for (Entree et : list2.keySet()) {
                if(et.getIdf().equals(variable) && !et.estFonction()){
                    symbole = list2.get(et) ;
                }
            }
        }

        return symbole;

    }


    /**
     * Fonction toString retourne l'idf variable
     * @return
     */
    @Override
    public String toString() {
        return variable;
    }


    /**
     * Fonction qui retourne le type de la variable
     * @return
     */
    @Override
    public Type getType() {
        return getSymbole().getType();
    }


    /**
     * Getteur
     * @return booleen est une variable
     */
    public boolean estVariable(){
        return true;
    }


    /**
     * Fonction estbool indique si expression est constantebool
     * @return
     */
    @Override
    public boolean estBool() {
        return (getSymbole().getType().getType().equals("booleen"));
    }

    /**
     * Fonction estFonction indique si expression est fonction
     * @return
     */
    @Override
    public boolean estFonction() {
        return false;
    }

    /**
     * Getteur idf
     * @return
     */
    @Override
    public String getIdf() {
        return variable;
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
