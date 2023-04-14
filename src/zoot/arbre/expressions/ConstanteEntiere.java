package zoot.arbre.expressions;

import zoot.tds.Type;

import java.util.List;

public class ConstanteEntiere extends Constante {

    /**
     * Constructeur
     * @param texte
     * @param n
     */
    public ConstanteEntiere(String texte, int n, int num) {
        super(texte, n,num) ;
    }


    /**
     * Fonction toMips, traduction en mips
     * @return le mips
     */
    @Override
    public String toMIPS(List<String> registres) {
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

    @Override
    public int getNombreDePlaces() {
        return 0;
    }

    @Override
    public String toMips(List<String> registres) {
        return null;
    }

    @Override
    public boolean estBinaire() {
        return false;
    }
}

