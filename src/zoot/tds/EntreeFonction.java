package zoot.tds;

public class EntreeFonction extends Entree{
    private int nbParam;

    /**
     * Constructeur
     *
     * @param idf
     * @param l
     */
    public EntreeFonction(String idf, int l, int numBloc, int nbParam) {
        super(idf, l, numBloc);
        this.nbParam =nbParam;
    }

    /**
     * Getteur
     * @return nombre de param
     */
    public int getNbParam() {
        return nbParam;
    }


    /**
     * Getteur
     * @return booleen est une fonction
     */
    public boolean estFonction(){
        return true;
    }

}


