package zoot.arbre.fonctions;

import zoot.arbre.FabriqueNumero;

import java.util.ArrayList;

public class GestionnaireFonctions {
    private ArrayList<Fonction> ListeFonctions;
    private static final GestionnaireFonctions instance = new GestionnaireFonctions();

    private GestionnaireFonctions(){
    }

    public static GestionnaireFonctions getInstance() {
        return instance;
    }

    public void ajouterFonction(Fonction fonction){
        this.ListeFonctions.add(fonction);
    }

}
