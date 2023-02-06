package zoot.exceptions;

public class AnalyseSemantiqueException extends AnalyseException{


    /**
     * Constructeur
     * @param m
     */
    public AnalyseSemantiqueException(String m) {
        super("ERREUR SEMANTIQUE : "+ m);
    }
}
