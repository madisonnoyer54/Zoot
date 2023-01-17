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

        code = exp.toMIPS()//"lw $v0,"+ exp.toMIPS()+"\n"+
                +"\tmove $a0, $v0  # Copie de la valeur de v0 dans a0\n"
                +"\tli $v0, 1  # v0 <-- 1 (code du print entier)\n"
                +"\tsyscall  # Afficher\n\n";
        return code;
        //throw new UnsupportedOperationException("fonction toMips non définie ") ;
    }

}
