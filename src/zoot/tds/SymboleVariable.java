package zoot.tds;

public class SymboleVariable extends Symbole{
    private int numVar;
    /**
     * Constructeur
     *
     * @param type
     */
    public SymboleVariable(String type, int num) {
        super(type);
        numVar =num;
    }

    /**
     * Fonction qui permet de savoir si l'entrer est une fonction
     * @return vrai si c'est une variable, faux sinon
     */
    public boolean estFonction(){
        return false;
    }

    public int getNumVar() {
        return numVar;
    }

    public void setNumVar(int numVar) {
        this.numVar = numVar;
    }
}
