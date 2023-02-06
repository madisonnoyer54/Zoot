package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;
import zoot.tds.Type;

public abstract class Expression extends ArbreAbstrait {

    /**
     * Constructeur
     * @param n
     */
    protected Expression(int n) {
        super(n) ;
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
}
