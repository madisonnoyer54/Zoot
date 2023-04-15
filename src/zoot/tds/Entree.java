package zoot.tds;

public abstract class Entree {
    public String idf;
    public int noLigne;

    public int numBloc;
    /**
     * Constructeur
     * @param idf
     */
    public Entree(String idf, int l, int numBloc){
        this.idf = idf;
        noLigne =l;
        this.numBloc = numBloc;
    }

    public int getNoLigne() {
        return noLigne;
    }

    public void setNoLigne(int noLigne) {
        this.noLigne = noLigne;
    }

    /**
     * Getteur
     * @return String nom idf
     */
    public String getIdf() {
        return idf;
    }


    /**
     * Setteur
     * @param idf
     */
    public void setIdf(String idf) {
        this.idf = idf;
    }

    /**
     * Fonction qui permet de savoir si l'entrer est une fonction
     * @return vrai si c'est une variable, faux sinon
     */
    public boolean estFonction(){
        return false;
    }

    /**
     * Getteur
     * @return numBloc
     */
    public int getNumBloc() {
        return numBloc;
    }
}
