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
     * @return
     */
    @Override
    public String toMIPS() {
        String ConstanteEntiere = cste;
        String code = "\tli $v0,"+ ConstanteEntiere+"  # On stock dans  v0\n";
        return code;
        //throw new UnsupportedOperationException("fonction toMips non définie ") ;
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
     * @return
     */
    public boolean estBool() {
        return false;
    }
}

