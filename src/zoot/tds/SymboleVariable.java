package zoot.tds;

public class SymboleVariable extends Symbole{
    /**
     * Constructeur
     *
     * @param type
     */
    public SymboleVariable(String type) {
        super(type);
    }

    /**
     * Fonction qui permet de savoir si l'entrer est une fonction
     * @return vrai si c'est une variable, faux sinon
     */
    public boolean estFonction(){
        return false;
    }
}
