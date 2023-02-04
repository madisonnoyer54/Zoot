package zoot.exceptions;

public class AnalyseVariableNonDeclare extends AnalyseException {

    public AnalyseVariableNonDeclare(String m) {
        super("ERREUR TYPE :\n\t" + m) ;
    }
}
