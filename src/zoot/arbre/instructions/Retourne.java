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
        code =  "# Met la valeur de l'expression dans $v0\n"+
                e.toMIPS();
                if (num > 0) { // si on est pas dans le main
                   code = code + "\n# Depile espace alloué aux variables locales\n" +
                            //"\taddi $sp,$sp, " + nbVar + "\n" + //OK
                            "\tmove $sp, $s7\n"+
                           "\t#Déplacement base\n" +
                            "\tlw $s7, 8($sp)\n" +
                           "\t#Dépiler le chainage dynamique\n" +
                            "\taddi $sp, $sp, 4\n" + //OK
                           "\t#Dépiler l'adresse retour\n"+
                           "\taddi $sp, $sp, 4\n" +
                           "\tlw $ra, 0($sp)\n" + //OK
                           "\t#Enregistre la valeur de $v0\n"+
                           "\tsw $v0, 4($sp)\n"+


                            "\t#Retour de fonction " + e.toString() +

                            "\tjr $ra\n";
                }
                else{
                    code = code +"# Fin du programme\n"+
                            "\tj end\n";
                }
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
