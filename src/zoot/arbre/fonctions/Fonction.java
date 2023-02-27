package zoot.arbre.fonctions;

import zoot.arbre.ArbreAbstrait;

public class Fonction {

    private final String idf;
    private final int noLigne;
    private final ArbreAbstrait arbre;

    public Fonction(int noLigne, String idf,ArbreAbstrait arbre) {
        this.idf = idf;
        this.noLigne = noLigne;
        this.arbre = arbre;
    }

    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n#Déclaration de la fonction ").append(this.idf).append("\n");
        sb.append(this.idf).append(": \n").append(this.arbre.toMIPS());
        sb.append("\tjr $ra"); //On retourne à l'appel de la fonction
        return sb.toString();
    }
}
