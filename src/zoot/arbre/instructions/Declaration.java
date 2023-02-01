package zoot.arbre.instructions;

public class Declaration extends Instruction{

    /**
     * Constructeur
     * @param n
     */
    protected Declaration(int n) {
        super(n);
    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {

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
