package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        //throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    @Override
    public String toMIPS() {
        String code;

        code = "# Ecrire "+exp.toString()+"\n"+
                exp.toMIPS()
                +"\tmove $a0, $v0  # Copie de la valeur de v0 dans a0\n"
                +"\tli $v0, 1  # v0 <-- 1 (code du print)\n"
                +"\tsyscall  # Afficher\n\n" +

                "# Ecrire une chaîne de caractères\n"+
                "\tla $a0, str # $a0 <- adresse de la chaîne à écrire\n" +
                "\tli $v0,4 # $v0 <- code du print\n" +
                "\tsyscall # afficher\n\n";

        return code;
        //throw new UnsupportedOperationException("fonction toMips non définie ") ;
    }

}
