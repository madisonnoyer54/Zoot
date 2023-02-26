package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.tds.TDS;

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
        /*
        sb.append("    #retour de fonction\n");
        sb.append("    #Met exp dans $v0\n");
        sb.append(exp.toMIPS() + "\n");
        if(idRegion > 0){
            sb.append("    #Deplacement dans la base\n");
            sb.append("    move $sp, $s7\n" );
            sb.append("    lw $s7, 8($sp)\n");
            sb.append("    #Depile l'id de la region\n");
            sb.append("    add $sp, $sp, 4\n\n");
            sb.append("    #Depile la chaine dynamique \n");
            sb.append("    add $sp, $sp, 4\n");
            sb.append("    #Depile l'adresse de retourne \n");
            sb.append("    add $sp, $sp, 4\n");
            sb.append("    lw $ra, 0($sp)\n");
            sb.append("    #Enregistre la valeur calculée dans $v0\n");
            sb.append("    sw $v0, 4($sp)\n");
            sb.append("    jr $ra\n");
        }else{
            sb.append("    #Direction fin du programme \n");
            sb.append("    j fin\n\n");
        }

         */
        return sb.toString();
    }

    public boolean estRetourner(){
        return true;
    }
}
