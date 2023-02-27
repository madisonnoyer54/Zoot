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
}
