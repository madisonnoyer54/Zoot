package zoot.arbre.instructions;

import zoot.arbre.FabriqueNumero;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;

import java.util.HashMap;

public class Ecrire extends Instruction {

    private int numero;
    protected Expression exp ;


    /**
     * Constructeur
     * @param e
     * @param n
     */
    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
        this.numero = FabriqueNumero.getInstance().genererNombre();
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
                Analyse.getInstance().ajouteIDF(exp.toString());
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
                if (exp.estBool()) {
                    code.append("\tbeq $zero, $a0, Sinon").append(this.numero).append("\n");
                    code.append("\tla $a0, vraiAff\n");
                    code.append("\tli $v0, 4\n");
                    code.append("\tsyscall\n");
                    code.append("\tb FinSi").append(this.numero).append("\n");
                    code.append("\tSinon").append(this.numero).append(":").append("\n");
                    code.append("\tla $a0, fauxAff\n");
                    code.append("\tli $v0, 4\n");
                    code.append("\tsyscall\n");
                    code.append("\tFinSi").append(this.numero).append(":").append("\n");
                } else {
                    code.append("\tli $v0, 1\n\tsyscall\n");
                }
                code.append("# Ecrire un saut de ligne\n"+
                "\tla $a0, str # $a0 <- adresse de la chaîne à écrire\n" +
                "\tli $v0,4 # $v0 <- code du print\n" +
                "\tsyscall # afficher\n\n");

        return code.toString();
        //throw new UnsupportedOperationException("fonction toMips non définie ") ;
    }

}
