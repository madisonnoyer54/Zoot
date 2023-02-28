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
        "\tadd $sp, $sp, 4\n" +
                "\tlw $s7, ($sp)\n" +
                "\tadd $sp, $sp, 4\n" +
                "\tlw $ra, ($sp)"+


        "\t#Retour de fonction"+ e.toString()    +
        "\n\tsw $v0, 0($sp)\n"+
                e.toMIPS()+
        "\tjr $ra\n";
        return code;
    }

    /**
     * Fonction qui donne le type de l'expression
     * @return
     */
    public Type getType(){
        return e.getType();
    }
    /**
     * Fonction qui indique si Instruction estRetourne (vrai)
     * @return
     */
    public boolean estRetourner(){
        return true;
    }
}
