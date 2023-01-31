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

    }

    public void identifier(String idf){}
}
