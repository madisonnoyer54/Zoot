package zoot.tds;

public class EntreeVariable extends Entree{
    /**
     * Constructeur
     *
     * @param idf
     * @param l
     */
    public EntreeVariable(String idf, int l) {
        super(idf, l);
    }


    /**
     * Getteur
     * @return booleen est n'est pas une fonction
     */
    public boolean estFonction(){
        return false;
    }
}
