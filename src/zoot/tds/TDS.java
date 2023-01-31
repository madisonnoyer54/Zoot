package zoot.tds;

import java.util.HashMap;
import java.util.Map;

public class TDS {
    private int compteurDeplace;
    private Map<String, Symbole> tableDesSymboles; //à revoir
    private static TDS instance = new TDS();

    public TDS() {
        this.tableDesSymboles = new HashMap<>();
    }

    public static TDS getInstance() {
        return instance;
    }

    public void ajouter (String idf, Symbole s){
        if (this.tableDesSymboles.containsKey(idf)) { // variable existe déjà double déclaration exception!!
        }
        this.tableDesSymboles.put(idf, s);
    }

    public Symbole identifier(String idf){
        if (!this.tableDesSymboles.containsKey(idf)) { //à générer exception variable non déclarée
        }
        return this.tableDesSymboles.get(idf);
    }
    public int getTailleZoneVariable(){
        return 0;éci
    }
}
