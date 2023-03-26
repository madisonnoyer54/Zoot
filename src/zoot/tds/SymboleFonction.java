package zoot.tds;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;

import java.util.ArrayList;

public class SymboleFonction extends Symbole{
    private BlocDInstructions blocDInstructions;
    private int nbParam;


    /**
     * Constructeur
     *
     * @param type
     */
    public SymboleFonction(String type, ArbreAbstrait li, int nbParam) {
        super(type);
        blocDInstructions = (BlocDInstructions)li;
        this.nbParam = nbParam;
    }

    /**
     * Fonction qui permet de savoir si l'entrer est une fonction
     * @return vrai si c'est une variable, faux sinon
     */
    public boolean estFonction(){
        return true;
    }

    public int getNb() {
        return nbParam;
    }

    public BlocDInstructions getBlocDInstructions() {
        return blocDInstructions;
    }
}
