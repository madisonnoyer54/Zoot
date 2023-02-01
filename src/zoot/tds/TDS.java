package zoot.tds;

import java.util.HashMap;
import java.util.Map;

public class TDS {
    private int compteurDeplace;
    private HashMap<String, Symbole> tableDesSymboles;
    private static TDS instance = new TDS();


    /**
     * Constructeur
     */
    public TDS() {
        this.tableDesSymboles = new HashMap<>();
    }


    /**
     * Getteur
     * @return instance
     */
    public static TDS getInstance() {
        return instance;
    }


    /**
     * Fonction ajouter, elle permet d'ajouter à la table des symboles si l'entrée n'est pas déja prise.
     * @param e
     * @param s
     */
    public void ajouter (Entree e, Symbole s){

        // variable existe déjà double déclaration exception!!
        if (this.tableDesSymboles.containsKey(e)) {
        }
        this.tableDesSymboles.put(e, s);
    }


    /**
     * Fonction identifier, elle permet d'identifier un symbole à partir de son entré
     * @param e
     * @return symbole
     */
    public Symbole identifier(Entree e){

        //à générer exception variable non déclarée
        if (!this.tableDesSymboles.containsKey(e)) {
        }
        return this.tableDesSymboles.get(e);
    }
    public int getTailleZoneVariable(){
        return 0;
    }
}
