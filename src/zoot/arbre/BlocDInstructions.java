package zoot.arbre;

import zoot.arbre.instructions.Instruction;
import zoot.tds.TDS;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<Instruction> programme ;


    /**
     * Constructeur
     * @param n
     */
    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }


    /**
     * Fonction ajouter, elle permet d'ajouter une instruction à la liste
     * @param i
     */
    public void ajouter(Instruction i) {
        programme.add(i) ;
    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
        //throw new UnsupportedOperationException("fonction verifier non définie ") ;
    }


    /**
     * Fonction toMips, traduction en mips
     * @return
     */
    @Override
    public String toMIPS() {
        String code;

        // Début du programme + str( permet le saut de ligne)
        code =
               ".data\n"+
                       "str: 	.asciiz \"\n\"\n"+
                ".text\n\n"+
                "main :\n\n";

        // Les déclaration des variables
        code = code +
                "# Initialiser $s7 avec $sp\n"+
                "\tmove $s7,$sp\n"+
                "# Reserve la place des variables\n"+
                "\taddi $sp,$sp,"+ TDS.getInstance().getTailleZoneVariable()*(-4)+ "\n\n";

        // Les Instructions
        for (Instruction instruction : programme) {
            code+=instruction.toMIPS();
        }

        // Fin du programme
        code =  code +"\nend :\n" +
                "\tli $v0, 10\n" +
                "\tsyscall";
        return code;

    }

    @Override
    public String toString() {
        return programme.toString() ;
    }

}
