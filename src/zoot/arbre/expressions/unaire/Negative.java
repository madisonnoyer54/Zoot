package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Type;

import java.util.List;

public class Negative extends Unaire {
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    public Negative(int n, int num,Expression e) {
        super(n, num,e);
    }

    @Override
    public void verifier() {
        if(this.e.estBool() ){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne +" : L'opérateurs dois etre un entiers "));

        }
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder("");



        code.append("\tli $v0,0\n");

        code.append("\tsw $v0, 0($sp)\n");

        code.append("\tadd $sp, $sp, -4 \n");

        code.append(e.toMIPS());
        code.append("\tadd $sp, $sp,4 \n");
        code.append("\tlw $t8, ($sp) \n");

        code.append("# Mise au négatif - \n");
        code.append("\tsub $v0, $t8, $v0\n");
        return code.toString();
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

}
