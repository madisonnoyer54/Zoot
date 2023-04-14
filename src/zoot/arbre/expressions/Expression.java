package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;
import zoot.tds.Type;

import java.util.List;

public abstract class Expression extends ArbreAbstrait {

    /**
     * Constructeur
     * @param n
     */
    protected Expression(int n, int num) {
        super(n,num) ;
    }


    /**
     * Getteur
     * @return type expression
     */
    public abstract Type getType();



    /**
     * Getteur
     * @return booleen est une variable
     */
    public boolean estVariable(){
        return false;
    }

    /**
     * Fonction qui permet de savoir si l'expression est booleen.
     * @return vrai si la constante est booleen, faux sinon
     */
    public abstract boolean estBool();

    /**
     * Fonction qui permet de savoir si l'expression est un appelle de fonction.
     * @return vrai si la constante est booleen, faux sinon
     */
    public abstract boolean estFonction();

    public abstract String getIdf();

    public abstract boolean estConstante();

    public  abstract int getNombreDePlaces();

    public abstract String toMIPS();

    public abstract void verifier();

    public abstract boolean estBinaire();
}
