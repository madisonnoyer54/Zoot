package zoot.arbre.expressions;

import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;

import java.util.HashMap;

public class Idf extends Expression {

    protected String variable;

    /**
     * Constructeur
     * @param texte
     * @param n
     */
    public Idf(String texte, int n) {
        super(n);
        variable = texte;

    }

    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }


    /**
     * Fonction toMips, traduction en mips
     * @return
     */
    @Override
    public String toMIPS() {
        return "\tlw $v0,"+getSymbole().getDeplacement() + "($s7)\n";
    }

    public Symbole getSymbole(){
        Symbole symbole = null;
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            if(et.getIdf().equals(variable)){
                symbole = TDS.getInstance().identifier(et);
            }
        }

        return symbole;
    }

    @Override
    public String toString() {
        return variable;
    }

    public boolean estVariable(){
        return true;
    }
}
