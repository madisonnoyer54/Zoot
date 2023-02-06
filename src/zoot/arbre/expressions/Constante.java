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
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
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


    /**
     * Fonction qui permet de savoir si l'expression est une variable
     * @return vrai si c'est une variable, faux sinon
     */
    public boolean estVariable(){
        return false;
    }

}
