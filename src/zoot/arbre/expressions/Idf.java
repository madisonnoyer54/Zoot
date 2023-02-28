package zoot.arbre.expressions;


import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;
import zoot.tds.Type;

import java.util.HashMap;

public class Idf extends Expression {

    protected String variable;


    /**
     * Constructeur
     * @param texte
     * @param n
     */
    public Idf(String texte, int n) {
        super(n);
        variable = texte;
        verifier();

    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
      // Symbole symbole = TDS.getInstance().identifier(new Entree(variable, noLigne));

    }


    /**
     * Fonction toMips, traduction en mips
     * @return le mips en string
     */
    @Override
    public String toMIPS() {
        return "\tlw $v0,"+getSymbole().getDeplacement() + "($s7)\n";
    }


    /**
     * Getteur
     * @return le symbole
     */
    public Symbole getSymbole(){
        Symbole symbole = null;
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            if(et.getIdf().equals(variable) && !et.estFonction()){
                symbole = TDS.getInstance().getTableDesSymboles().get(et) ;
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
}
