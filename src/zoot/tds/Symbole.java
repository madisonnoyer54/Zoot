package zoot.tds;

public class Symbole {
    private String type;
    private int deplacement;

    public Symbole(String type, int depl) {
        this.type = type;
        this.deplacement = depl;
    }

    public String getType() {
        return type;
    }

    public int getDeplacement() {
        return deplacement;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }
}
