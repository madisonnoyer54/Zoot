package zoot.tds;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;

public class SymboleFonction extends Symbole{
    private BlocDInstructions blocDInstructions;
    /**
     * Constructeur
     *
     * @param type
     */
    public SymboleFonction(String type, ArbreAbstrait li) {
        super(type);
        blocDInstructions = (BlocDInstructions)li;
    }

    /**
     * Fonction qui permet de savoir si l'entrer est une fonction
     * @return vrai si c'est une variable, faux sinon
     */
    public boolean estFonction(){
        return true;
    }

    public BlocDInstructions getBlocDInstructions() {
        return blocDInstructions;
    }
}
