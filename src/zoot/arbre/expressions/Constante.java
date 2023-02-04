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
     * @return
     */
    @Override
    public String toString() {
        return cste ;
    }

    public Type getType(){
        return null;
    }

    public boolean estVariable(){
        return false;
    }

}
