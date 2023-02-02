package zoot.arbre.expressions;

import zoot.tds.Entree;
import zoot.tds.TDS;

public class Idf extends Expression {


    protected String variable;
    private int depl;

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
        return this.depl + "($s7)";
    }
}
