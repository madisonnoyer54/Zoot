package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.tds.TDS;
import zoot.tds.Type;

public class Retourne extends Instruction{
    private Expression e;
    private int num;

    /**
     * Constructeur
     *
     * @param n
     */
    public Retourne(Expression e, int n, int num) {
        super(n, num);
        this.e = e;
        this.num=num;

    }

    @Override
    public void verifier() {
        e.verifier();

    }


    @Override
    public String toMIPS() {
        String code;
        int nbVar=TDS.getInstance().getTailleZoneVariable(num);
        // depiler s7 et ra
        code = "\n# Depile de s7 et ra\n"+
                "\taddi $sp,$sp, "+nbVar+"\n"+
                //"\tadd $sp, $sp, 4\n" +
                "\tlw $s7, ($sp)\n" +
                "\tadd $sp, $sp, 4\n" +
                "\tlw $ra, ($sp)"+


        "\t#Retour de fonction "+ e.toString()    +
        //"\n\tsw $v0, 0($sp)\n"+
        //       e.toMIPS()+ (à revoir ici)
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
