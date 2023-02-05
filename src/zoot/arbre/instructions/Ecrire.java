package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.AnalyseVariableNonDeclare;
import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;

import java.util.HashMap;

public class Ecrire extends Instruction {

    protected Expression exp ;


    /**
     * Constructeur
     * @param e
     * @param n
     */
    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
        if(exp.estVariable()){
            // Vérifie que la variable à étais initialiser
            boolean rep = false;
            HashMap<Entree, Symbole> list = TDS.getInstance().getTableDesSymboles();
            for (Entree et : list.keySet()) {
                if(et.getIdf().toString().equals(exp.toString())){
                    rep = true;
                }
            }
            if(!rep){
                throw new AnalyseVariableNonDeclare("La variable dans ecrire "+ exp.toString()+ " n'a pas été déclarer");
            }
        }

    }


    /**
     * Fonction toMips, traduction en mips
     * @return
     */
    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder();

        code.append("# Ecrire "+exp.toString()+"\n"+
                exp.toMIPS()
                +"\tmove $a0, $v0  # Copie de la valeur de v0 dans a0\n");
                if(exp.estBool()){
                    code.append("\n# Initialiser $t8 avec la valeur faux\n");
                    code.append("\tla $t8, faux\n");
                    code.append("\tbeq $t8, $v0, Sinon").append("\n");
                    code.append("\tla $a0, vraiAff\n");
                    code.append("\tli $v0, 4\n");
                    code.append("\tsyscall\n");
                    code.append("\tb FinSi").append("\n");
                    code.append("Sinon").append(":").append("\n");
                    code.append("\tla $a0, fauxAff\n");
                    code.append("\tli $v0, 4\n");
                    code.append("\tsyscall\n");
                    code.append("FinSi").append(":\n");
                }
                else {
                    code.append("\tmove $a0, $v0\n"); //On met le résultat de l'expression
                    code.append("\tli $v0, 1\n");
                    code.append("\tsyscall\n");
                }
                code.append("# Ecrire un saut de ligne\n"+
                "\tla $a0, str # $a0 <- adresse de la chaîne à écrire\n" +
                "\tli $v0,4 # $v0 <- code du print\n" +
                "\tsyscall # afficher\n\n");

        return code.toString();
        //throw new UnsupportedOperationException("fonction toMips non définie ") ;
    }

}
