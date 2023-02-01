package zoot.exceptions;

public class AnalyseSyntaxiqueException extends AnalyseException {


    /**
     * Constructeur
     * @param m
     */
    public AnalyseSyntaxiqueException(String m) {
        super("ERREUR SYNTAXIQUE :\n\t" + m) ;
    }

}
