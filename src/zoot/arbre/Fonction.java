package zoot.arbre;

import zoot.arbre.expressions.Expression;
import zoot.arbre.instructions.Instruction;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Type;

import java.util.HashMap;

public class Fonction extends Instruction {
    private BlocDInstructions il;
    private String nom;

    /**
     * Constructeur
     * @param n
     */
    public Fonction(String t, String nom,BlocDInstructions il, int n) {
        super(n);
        this.nom = nom;
        this.il = il;
    }

    @Override
    public void verifier() {

        Boolean result = false;
        for (Instruction instruction : il.programme) {
           // instruction.verifier();

            // Verifie qu'il y a un retourner
            if(instruction.estRetourner()){
                result = true;
            }
        }

        if(result == false){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne+1+" : Une fonction dois avoir obligatoirement un retourne ("+ nom+")."));
        }

    }

    @Override
    public String toMIPS() {

        StringBuilder sb = new StringBuilder();
        /*
        sb.append("    #Fonction\n" +  + ":\n");
        sb.append("    #Empile l'adresse de retour\n");
        sb.append("    sw $ra, 0($sp)\n");
        sb.append("    add $sp, $sp, -4\n" + "\n");
        sb.append("    #Empilement chainage dynamique\n");
        sb.append("    sw $s7, 0($sp)\n");
        sb.append("    add $sp, $sp, -4\n" + "\n");
        sb.append("    #Empilement de l'id de la region\n");
        sb.append("    li $t8, " + idBloc + "\n");
        sb.append("    sw $t8, 0($sp)\n");
        sb.append("    add $sp, $sp, -4\n" + "\n");
        sb.append("    #Deplacement de la base\n");
        sb.append("    move $s7, $sp\n" + "\n");
        sb.append("    #Allocation des variables \n");
        sb.append("    add $sp, $sp , " + this.memoryVar+ "\n");
        sb.append("    # initialisation de toutes les variables a 0\n") ;

        for(int dep = 0; dep < this.memoryVar; dep += 4) {
            sb.append("sw $zero, -");
            sb.append(dep);
            sb.append("($s7)\n");
        }

        sb.append("#Instruction dans la fonction\n");
        if (bloc2!=null){
            sb.append(bloc2.toMIPS());
        }
        sb.append(bloc.toMIPS());
        sb.append("\n");

         */
        return sb.toString();
    }


    @Override
    public boolean estRetourner() {
        return false;
    }
}
