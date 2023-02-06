package zoot.exceptions;

public class AnalyseVariable extends AnalyseException{
    /**
     * Constructeur
     *
     * @param m
     */
    protected AnalyseVariable(String m) {
        super("ERREUR VARIABLE" + m);
    }
}
