package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.tds.Type;

import java.util.ArrayList;

public abstract class Instruction extends ArbreAbstrait {


    /**
     * Constructeur
     * @param n
     */
    protected Instruction(int n, int numBloc) {
        super(n,numBloc);
    }

    /**
     * Fonction qui retourne vrai si l'instruction est un retourner, faux sinon
     * @return
     */
    public abstract boolean estRetourner();

    /**
     * Getteur
     * @return type
     */
    public Type getType() {
        return null;
    }


    /**
     * FOnction qui retourne vrai si l'instruction est un condition ou une boucle
     * @return
     */
    public abstract boolean estBoucleOuCondition();


    /**
     * Fonction qui retourne vrai si l'instruction contient un retourner, faux sinon
     * @return
     */
    public abstract Boolean contientRetourner();


    /**
     * Getteur
     * @return l'instruction retourner
     */
    public abstract Instruction getRetourner();

    /**
     * Fonction qui retourne vrai si l'instruction est une condition, faux sinon
     * @return
     */
    public abstract boolean estCondition();

}
