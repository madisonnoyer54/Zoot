package zoot.arbre.expressions;

import zoot.tds.Type;

public abstract class Constante extends Expression {

    protected String cste ;

    /**
     * Constructeur
     * @param texte
     * @param n
     */
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {

    }


    /**
     * Fonction toString
     * @return la constante
     */
    @Override
    public String toString() {
        return cste ;
    }


    /**
     * Getteur
     * @return le type
     */
    public abstract Type getType();

    @Override
    public String getIdf() {
        return null;
    }
    /**
     * Fonction qui permet de savoir si l'expression est une variable
     * @return vrai si c'est une variable, faux sinon
     */
    public boolean estVariable(){
        return false;
    }

}
