package zoot.arbre.expressions;

import zoot.tds.Type;

public abstract class Constante extends Expression {

    protected String cste ;

    /**
     * Constructeur
     * @param texte
     * @param n
     */
    protected Constante(String texte, int n, int num) {
        super(n, num) ;
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

    @Override
    public boolean estVariable(){
        return false;
    }

    @Override
    public boolean estConstante(){
        return true;
    }
}
