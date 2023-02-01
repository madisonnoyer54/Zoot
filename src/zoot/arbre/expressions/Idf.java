package zoot.arbre.expressions;

public class Idf extends Expression {

    protected String variable;
    public Idf(String texte, int n) {
        super(n);
        variable = texte;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    @Override
    public String toMIPS() {
        return null;
    }

}
