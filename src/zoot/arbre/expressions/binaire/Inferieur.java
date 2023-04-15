package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Type;

import java.util.List;

public class Inferieur extends Binaire {
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    public Inferieur(int n, int num, Expression e1, Expression e2) {
        super(n, num,e1,e2);
    }

    @Override
    public Type getType() {
        return Type.BOOLEEN;
    }

    @Override
    public boolean estBool() {
        return true;
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
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne +" : Les opérateurs d'une < doivent etre des entiers "));

        }
    }

    @Override
    public String toString() {
        return e1 + "<"+e2;
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder();

        code.append("# Ecriture < \n");

        code.append("# Calcul de l'expression gauche\n");
        code.append(e1.toMIPS());
        code.append("\n");

        code.append("# Empilement de l'expression gauche\n");
        code.append("sw $v0, 0($sp)\n");
        code.append("add $sp, $sp, -4\n");
        code.append("\n");

        code.append("# Calcul de l'expression droite\n");
        code.append(e2.toMIPS());
        code.append("\n");

        code.append("# Dépilement de l'expression gauche\n");
        code.append("add $sp, $sp, 4\n");
        code.append("lw $t8, 0($sp)\n");
        code.append("\n");

        code.append("slt $v0, $t8, $v0\n");

        return code.toString();
    }
    @Override
    public boolean estBinaire() {
        return true;
    }

}
