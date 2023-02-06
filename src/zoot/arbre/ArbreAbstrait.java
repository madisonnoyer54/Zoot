package zoot.arbre;

public abstract class ArbreAbstrait {
    
    // numéro de ligne du début de l'instruction
    protected int noLigne ;


    /**
     * Constructeur
     * @param n
     */
    protected ArbreAbstrait(int n) {
        noLigne = n ;
    }


    /**
     * Getteur
     * @return numéro de ligne du début de l'instruction
     */
    public int getNoLigne() {
            return noLigne ;
    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    public abstract void verifier() ;


    /**
     * Fonction toMips, traduction en mips
     * @return
     */
    public abstract String toMIPS();

}
