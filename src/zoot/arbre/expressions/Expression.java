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


    /**
     * Getteur
     * @return IDF
     */
    public abstract String getIdf();

    /**
     * Fonction qui return true si c'est une constente faux sinon
     * @return
     */
    public abstract boolean estConstante();


    /**
     * Getteur
     * @return nombre de place
     */
    public  abstract int getNombreDePlaces();


    /**
     * Fonction du mips
     * @return le mips
     */
    public abstract String toMIPS();


    /**
     * Fonction qui verifie le code
     */
    public abstract void verifier();


    /**
     * Fonction qui retourne vrai si l'expression est binaire, faux sinon
     * @return
     */
    public abstract boolean estBinaire();
}
