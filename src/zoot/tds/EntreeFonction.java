package zoot.tds;

public class EntreeFonction extends Entree{
    /**
     * Constructeur
     *
     * @param idf
     * @param l
     */
    public EntreeFonction(String idf, int l, int numBloc) {
        super(idf, l, numBloc);
    }



    /**
     * Getteur
     * @return booleen est une fonction
     */
    public boolean estFonction(){
        return true;
    }

}


