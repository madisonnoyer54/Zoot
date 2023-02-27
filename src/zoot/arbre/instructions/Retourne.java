package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.tds.TDS;
import zoot.tds.Type;

public class Retourne extends Instruction{
    private Expression e;


    /**
     * Constructeur
     *
     * @param n
     */
    public Retourne(Expression e, int n) {
        super(n);
        this.e = e;

    }

    @Override
    public void verifier() {
        e.verifier();

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("    #retour de fonction\n");
        sb.append("\tli, $v0, " + e.toMIPS() + "\n");
        sb.append("\tjr $ra\n");
        return sb.toString();
    }

    public Type getType(){
        return e.getType();
    }
    public boolean estRetourner(){
        return true;
    }
}
