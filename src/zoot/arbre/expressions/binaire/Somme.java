package zoot.arbre.expressions.binaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Type;

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


    public String toMIPS() {
        StringBuilder code = new StringBuilder();

        String sbg =  e1.toMIPS() ;//expression gauche

        String sbd = e2.toMIPS() ;

        code.append("#traitement operande gauche");
        code.append(sbg);
        code.append("\tsw $v0, 0($sp)\n");
        code.append("\tadd $sp, $sp, -4 \n");
        code.append(sbd);
        code.append("\tadd $sp, $sp,4 \n");
        code.append("\tlw $t8, ($sp) \n");


        code.append("# Resultat addition \n");
        code.append("\tadd $v0, $t8, $v0\n");

        return code.toString();
    }

    @Override
    public void verifier() {
        if(this.e1.estBool() || e2.estBool()){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne +" : Les opérateurs d'une somme doivent etre des entiers "));

        }
    }

    @Override
    public String toString() {
        return e1 + "+"+e2;
    }
}
