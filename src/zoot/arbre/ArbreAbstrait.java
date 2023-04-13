package zoot.arbre;

import java.util.List;

public abstract class ArbreAbstrait {
    
    // numéro de ligne du début de l'instruction
    protected int noLigne ;
    protected int numBloc;


    /**
     * Constructeur
     * @param n
     */
    protected ArbreAbstrait(int n, int num) {
        noLigne = n ;
        numBloc = num;
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
    public abstract String toMIPS(List<String> registres);

    public int getNumBloc() {
        return numBloc;
    }

    public void setNumBloc(int numBloc) {
        this.numBloc = numBloc;
    }


}
