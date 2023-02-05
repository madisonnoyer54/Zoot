package zoot.exceptions;

public class AnalyseVaribleNomDejaPris extends AnalyseException{
    /**
     * Constructeur
     *
     * @param m
     */
    public AnalyseVaribleNomDejaPris(String m) {
        super("ERREUR NOM VARIABLE" + m);
    }
}
