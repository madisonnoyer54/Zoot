package zoot.tds;

import java.util.HashMap;
import java.util.Map;

public class TDS {
    private int compteurDeplace;
    private Map<Entree, Symbole> tableDesSymboles;

    public TDS() {
        this.tableDesSymboles = new HashMap<>();
    }
}
