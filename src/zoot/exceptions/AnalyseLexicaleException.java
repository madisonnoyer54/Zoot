package zoot.exceptions;

public class AnalyseLexicaleException extends AnalyseException {


    /**
     * Constructeur
     * @param ligne
     * @param colonne
     * @param m
     */
    public AnalyseLexicaleException(int ligne, int colonne, String m) {
        super("ERREUR LEXICALE :\n\tligne " + ligne + " colonne " + colonne + "\n\t" + m + " : caractère non reconnu") ;
    }

}
