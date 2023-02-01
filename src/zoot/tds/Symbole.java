package zoot.tds;

public class Symbole {
    private String type;
    private int deplacement;

    /**
     * Constructeur
     * @param type
     * @param depl
     */
    public Symbole(String type, int depl) {
        this.type = type;
        this.deplacement = depl;
    }

    /**
     * Getteur
     * @return le type
     */
    public String getType() {
        return type;
    }


    /**
     * Getteur
     * @return le deplacement
     */
    public int getDeplacement() {
        return deplacement;
    }


    /**
     * Setteur
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Setteur
     * @param deplacement
     */
    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }
}
