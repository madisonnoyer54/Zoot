package zoot.arbre.expressions;

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
        return null;
    }


}
