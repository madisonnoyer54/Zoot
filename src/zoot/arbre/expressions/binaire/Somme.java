package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Type;

import java.util.List;

public class Somme extends Binaire {

    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    public Somme(int n, int num, Expression e1, Expression e2) {
        super(n, num,e1,e2);
    }

    @Override
    public Type getType() {
        return null;
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
    public String toMips(List<String> registres) {
        StringBuilder code = new StringBuilder();
        code.append(e1.toMIPS(registres));
        //Si on a encore au moins 1 registre temporaire disponible, on l'utilise
        if (registres.size() != 1) {
            //On stocke le résultat dans un registre pour pouvoir réutiliser v0
            code.append("\tmove ").append(registres.get(1)).append(", ").append(registres.get(0)).append("\n");

            //code.append(e2.toMIPS(supprRegistreInutile(1, registres)));
            //On ajoute les 2 entiers stockés dans les 2 registres, puis stocke le résultat de la somme de v0.
            code.append("\tadd ").append(registres.get(0)).append(",").append(registres.get(0)).append(",").append(registres.get(1)).append("\n");
        } else { //Sinon, on utilise la pile
            code.append("\tsw $v0,($sp)\n");
            code.append("\tadd $sp,$sp,-4\n");
            code.append(e2.toMIPS(registres)).append("\n");
            code.append("\tadd $sp,$sp,4\n");
            code.append("\tlw $t8,($sp)\n");
            code.append("\tadd $v0, $t8, $v0\n");
        }
        return code.toString();
    }

    @Override
    public void verifier() {
        if(this.e1.estBool() || e2.estBool()){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne +" : Les opérateurs d'une somme doivent etre des entiers "));

        }
    }

    @Override
    public String toMIPS(List<String> registres) {
        return null;
    }

    @Override
    public String toString() {
        return e1 + "+"+e2;
    }
}
