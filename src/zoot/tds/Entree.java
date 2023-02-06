package zoot.tds;

public class Entree {
    String idf;

    /**
     * Constructeur
     * @param idf
     */
    public Entree(String idf){
        this.idf = idf;
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
