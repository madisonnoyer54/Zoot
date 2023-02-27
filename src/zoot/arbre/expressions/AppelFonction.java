package zoot.arbre.expressions;

import zoot.tds.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AppelFonction extends Expression{
    private String idf;
    private int n;
    public AppelFonction(String idf,int n) {
        super(n);
        this.idf = idf;
        this.n=n;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n# Sauvegarde des registres avant l'appel de fonction.\n");
        sb.append("\tsw $ra,0($sp)\n");
        sb.append("\tsw $s1,-4($sp)\n");
        sb.append("\taddi $sp,$sp,-8\n");
        sb.append("\t# Appel de la fonction.\n");
        sb.append("\tjal ").append(this.idf).append("\n");
        sb.append("\t# Restauration des registres après l'appel de fonction.\n");
        sb.append("\tlw $s1,4($sp)\n");
        sb.append("\tlw $ra,8($sp)\n");
        sb.append("\taddi $sp,$sp,8\n");
        return sb.toString();
    }

    /**
     * Getteur
     * @return le symbole
     */
    public Symbole getSymbole(){
        Symbole symbole = null;
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            if(et.getIdf().equals(idf) && et.estFonction()){
                symbole = TDS.getInstance().getTableDesSymboles().get(et) ;
            }
        }

        return symbole;
    }

    @Override
    public Type getType() {
        //On récupère le type de retour de la fonction
        return getSymbole().getType();
    }


    @Override
    public boolean estBool() {
        return false;
    }

    @Override
    public boolean estFonction() {
        return true;
    }

    public String getIdf() {
        return idf;
    }


}
