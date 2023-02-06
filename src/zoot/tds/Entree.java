package zoot.tds;

public class Entree {
    public String idf;
    public int noLigne;

    /**
     * Constructeur
     * @param idf
     */
    public Entree(String idf, int l){
        this.idf = idf;
        noLigne =l;
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
}
