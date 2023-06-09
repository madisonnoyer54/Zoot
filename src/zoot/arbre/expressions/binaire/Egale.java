package zoot.arbre.expressions.binaire;

import zoot.arbre.FabriqueNumero;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Type;

import java.util.List;

public class Egale extends Binaire {
    public int numero;
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    public Egale(int n, int num, Expression e1, Expression e2) {
        super(n, num,e1,e2);
        this.numero = FabriqueNumero.getInstance().genererNombre();
    }

    @Override
    public Type getType() {
        return Type.BOOLEEN;
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
        if(this.e1.getType() != e2.getType()){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne +" : Les opérateurs d'une == doivent etre de même type "));

        }
    }

    @Override
    public boolean estBinaire() {
        return true;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder(100);

        code.append("# Ecriture == \n");

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

        code.append("seq $v0, $v0, $t8\n");

        return code.toString();

    }
}
