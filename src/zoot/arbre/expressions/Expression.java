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


    public Type getType(){
        return null;
    }
}
