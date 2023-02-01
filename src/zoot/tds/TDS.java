package zoot.tds;

import java.util.HashMap;
import java.util.Map;

public class TDS {
    private int compteurDeplace;
    private HashMap<String, Symbole> tableDesSymboles; //à revoir
    private static TDS instance = new TDS();

    public TDS() {
        this.tableDesSymboles = new HashMap<>();
    }

    public static TDS getInstance() {
        return instance;
    }

    public void ajouter (Entree e, Symbole s){
        if (this.tableDesSymboles.containsKey(e)) { // variable existe déjà double déclaration exception!!
        }
        this.tableDesSymboles.put(e, s);
    }

    public Symbole identifier(Entree e){
        if (!this.tableDesSymboles.containsKey(e)) { //à générer exception variable non déclarée
        }
        return this.tableDesSymboles.get(e);
    }
    public int getTailleZoneVariable(){
        return 0;
    }
}
