package zoot.exceptions;

public class AnalyseVariableNomDejaPris extends AnalyseException{
    /**
     * Constructeur
     *
     * @param m
     */
    public AnalyseVariableNomDejaPris(String m) {
        super("ERREUR NOM VARIABLE" + m);
    }
}
