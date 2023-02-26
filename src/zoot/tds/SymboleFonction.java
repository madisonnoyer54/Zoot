package zoot.tds;

import zoot.arbre.ArbreAbstrait;

public class SymboleFonction extends Symbole{
    private ArbreAbstrait arbreAbstrait;
    /**
     * Constructeur
     *
     * @param type
     */
    public SymboleFonction(String type, ArbreAbstrait li) {
        super(type);
        arbreAbstrait = li;
    }

    /**
     * Fonction qui permet de savoir si l'entrer est une fonction
     * @return vrai si c'est une variable, faux sinon
     */
    public boolean estFonction(){
        return true;
    }
}
