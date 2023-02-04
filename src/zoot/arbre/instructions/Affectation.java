package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;

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
    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {

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
                "\tsw, $v0, "+ variable.toMIPS()+"\n\n";

        return code;
    }
}
