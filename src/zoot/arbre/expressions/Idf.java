package zoot.arbre.expressions;

import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;
import zoot.tds.Type;

import java.util.HashMap;

public class Idf extends Expression {

    protected String variable;
    private Symbole symbole;

    /**
     * Constructeur
     * @param texte
     * @param n
     */
    public Idf(String texte, int n) {
        super(n);
        variable = texte;
        verifier();

    }

    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
        int result = 0;
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            if(et.getIdf().toString().equals(variable.toString())){
               result += 1;
                this.symbole = TDS.getInstance().identifier(et);
            }
        }

        // Test si la variable a été déclarer
        if (result == 0){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(" La variable "+ variable.toString()+ " n'est pas déclaré"));
        }
    }


    /**
     * Fonction toMips, traduction en mips
     * @return
     */
    @Override
    public String toMIPS() {
        return "\tlw $v0,"+getSymbole().getDeplacement() + "($s7)\n";
    }

    public Symbole getSymbole(){
        Symbole symbole = null;
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            if(et.getIdf().equals(variable)){
                symbole = TDS.getInstance().identifier(et);
            }
        }

        return symbole;
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public Type getType() {
        return symbole.getType();
    }

    /**
     * Getteur
     * @return booleen est une variable
     */
    public boolean estVariable(){
        return true;
    }

    @Override
    public boolean estBool() {
        return (symbole.getType().getType().equals("booleen"));
    }
}
