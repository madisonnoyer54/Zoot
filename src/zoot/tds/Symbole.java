package zoot.tds;

public abstract class Symbole{
    private Type type;
    private int deplacement;


    /**
     * Constructeur
     * @param type
     */
    public Symbole(String type) {
        this.type = Type.ENTIER.quelleType(type);
        deplacement = 0;
    }


    /**
     * Getteur
     * @return le type
     */
    public Type getType() {
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
    public void setType(Type type) {
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
