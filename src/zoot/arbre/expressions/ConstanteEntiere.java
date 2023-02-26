package zoot.arbre.expressions;

import zoot.tds.Type;

public class ConstanteEntiere extends Constante {

    /**
     * Constructeur
     * @param texte
     * @param n
     */
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }


    /**
     * Fonction toMips, traduction en mips
     * @return le mips
     */
    @Override
    public String toMIPS() {
        String ConstanteEntiere = cste;
        String code = "\tli $v0,"+ ConstanteEntiere+"  # On stock dans  v0\n";
        return code;
    }


    /**
     * Getteur
     * @return type de la constante
     */
    public Type getType(){
        return Type.ENTIER;
    }


    /**
     * Getteur
     * @return booleen est une variable
     */
    public boolean estVariable(){
        return false;
    }


    /**
     * Fonction qui indique si la constante est booléene
     * @return vrai si la constante est booleen, faux sinon
     */
    public boolean estBool() {
        return false;
    }

    @Override
    public boolean estFonction() {
        return false;
    }
}

