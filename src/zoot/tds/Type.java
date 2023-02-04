package zoot.tds;

public enum Type {
    ENTIER("entier"),
    BOOLEEN("booleen");
    String type;

    /**
     * Constructeur
     * @param type
     */
    Type(String type) {
        this.type = type;

    }


    /**
     * Getteur
     * @return type en String
     */
    public String getType() {
        return type;
    }


    /**
     * Setteur
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * Fonction qui permet de savoir le type du string donner
     * @param type
     * @return
     */
    public Type quelleType(String type){
        if(type == "entier"){
            return Type.ENTIER;
        }
        return Type.BOOLEEN;
    }
}
