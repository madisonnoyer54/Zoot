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
        String code;
        // depiler s7 et ra
        code = "\n# Depile de s7 et ra\n"+
        "\tadd $sp, $sp, 4\n"+
        "\tadd $ra, $ra, 4\n"+

        "\t#Retour de fonction"+ e.toString()    +
        "\n\t sw $v0, -4($sp)\n"+
        "\tjr $ra\n";
        return code;
    }

    public Type getType(){
        return e.getType();
    }
    public boolean estRetourner(){
        return true;
    }
}
