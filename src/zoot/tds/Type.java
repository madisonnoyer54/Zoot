package zoot.tds;

public enum Type {
    ENTIER("entier"),
    BOOLEEN("booleen");
    String type;

    Type(String type) {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Type quelleType(String type){
        if(type == "entier"){
            return Type.ENTIER;
        }
        return Type.BOOLEEN;
    }
}
