package zoot.arbre;

import zoot.arbre.instructions.Instruction;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<Instruction> programme ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(Instruction i) {
        programme.add(i) ;
    }

    @Override
    public void verifier() {
        //throw new UnsupportedOperationException("fonction verifier non définie ") ;
    }
    
    @Override
    public String toMIPS() {
        String code;
        code =
               ".data\n"+
                       "str: 	.asciiz\n"+
                ".text\n\n"+
                "main :\n\n";

        for (Instruction instruction : programme) {
            code+=instruction.toMIPS();
        }
        code =  code +"\nend :\n" +
                "\tli $v0, 10\n" +
                "\tsyscall";
        return code;
        //throw new UnsupportedOperationException("fonction toMips non définie ") ;
    }

    @Override
    public String toString() {
        return programme.toString() ;
    }

}
