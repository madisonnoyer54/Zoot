package zoot.arbre.instructions;

import zoot.arbre.FabriqueNumero;
import zoot.arbre.expressions.AppelFonction;
import zoot.arbre.expressions.Expression;
import zoot.tds.*;


public class Ecrire extends Instruction {

    private int numero;
    protected Expression exp ;


    /**
     * Constructeur
     * @param e
     * @param n
     */
    public Ecrire (Expression e, int n, int num) {
        super(n, num) ;
        exp = e ;
        this.numero = FabriqueNumero.getInstance().genererNombre();
    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
        int taille;
        exp.verifier();

        if (!exp.estFonction()){
            Symbole symbole =  TDS.getInstance().identifier(new EntreeVariable(exp.getIdf(), noLigne,numBloc));
        }
        else{
            AppelFonction a = (AppelFonction) exp;
            if(a.getListParam() == null){
                taille =0;
            }else{
                taille = a.getListParam().size();
            }
            Symbole symbole =  TDS.getInstance().identifier(new EntreeFonction(exp.getIdf(), noLigne,numBloc,taille));

        }

    }


    /**
     * Fonction toMips, traduction en mips
     * @return le mips en string
     */
    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder();
        code.append("# Ecrire "+exp.toString()+"\n"+
                exp.toMIPS()
                +"\tmove $a0, $v0  # Copie de la valeur de v0 dans a0\n");
                if (exp.estBool()||exp.getType().equals(Type.BOOLEEN)) {
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

    /**
     * Fonction qui indique si Instruction estRetourne
     * @return
     */
    @Override
    public boolean estRetourner() {
        return false;
    }

    @Override
    public boolean estBoucleOuCondition() {
        return false;
    }

    @Override
    public Boolean contientRetourner() {
        return null;
    }

    @Override
    public Instruction getRetourner() {
        return null;
    }

    @Override
    public boolean estCondition() {
        return false;
    }
}
