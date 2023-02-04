package zoot.exceptions;

public class AnalyseTypeException extends AnalyseException{
    /**
     * Constructeur
     * @param m, le message a*à afficher
     */
    public AnalyseTypeException(String m) {
        super("ERREUR TYPE :\n\t" + m) ;
    }
}
