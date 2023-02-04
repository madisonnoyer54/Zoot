package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.AnalyseException;
import zoot.exceptions.AnalyseSyntaxiqueException;
import zoot.exceptions.AnalyseTypeException;

public class Affectation extends Instruction{
    protected Expression exp ;
    protected Idf variable;


    /**
     * Constructeur
     * @param idf
     * @param e
     * @param n
     */
    public Affectation(String idf,Expression e,int n) {
        super(n);
        variable = new Idf(idf,n);
        exp = e;
        verifier();
    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
        if(!variable.getSymbole().getType().concordance(exp.getType())){
            throw new AnalyseTypeException("Le type de la variable "+ variable.toString()+" n'est pas de même type de l'expression");
        }
    }


    /**
     * Fonction toMips, traduction en mips
     * @return
     */
    @Override
    public String toMIPS() {
        // On met dans v0
        String code = "";
        code += "# Affectation (" + variable.toString() +" = "+ exp.toString()+")\n"+
                exp.toMIPS()+
                "\tsw $v0, "+ variable.getSymbole().getDeplacement()+"($s7)" +"\n\n";

        return code;
    }
}
