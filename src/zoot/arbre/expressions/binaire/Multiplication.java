package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Type;

import java.util.List;

public class Multiplication extends Binaire {
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    public Multiplication(int n, int num, Expression e1, Expression e2) {
        super(n, num,e1,e2);
    }

    @Override
    public Type getType() {
        return Type.ENTIER;
    }

    @Override
    public boolean estBool() {
        return false;
    }

    @Override
    public boolean estFonction() {
        return false;
    }

    @Override
    public String getIdf() {
        return null;
    }

    @Override
    public boolean estConstante() {
        return false;
    }

    @Override
    public int getNombreDePlaces() {
        return 0;
    }

    @Override
    public void verifier() {
        if(this.e1.estBool() || e2.estBool()){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne +" : Les opérateurs d'une multiplication doivent etre des entiers "));

        }
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder();

        String seg =  e1.toMIPS() ;//expression gauche

        String sed = e2.toMIPS() ;

        code.append("#traitement de l'expression gauche\n");
        code.append(seg);
        code.append("\tsw $v0, 0($sp)\n");
        code.append("\tadd $sp, $sp, -4 \n");
        code.append("#traitement de l'expression droite\n");
        code.append(sed);
        code.append("\tadd $sp, $sp,4 \n");
        code.append("\tlw $t8, ($sp) \n");


        code.append("# Multiplication \n");
        code.append("\tmult $t8, $v0\n");
        code.append("\tmflo $v0\n");

        return code.toString();
    }
    @Override
    public boolean estBinaire() {
        return true;
    }

}
