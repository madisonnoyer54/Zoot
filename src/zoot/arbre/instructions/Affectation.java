package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.*;

import java.util.HashMap;

public class Affectation extends Instruction{
    protected Expression exp ;
    protected Idf variable;


    /**
     * Constructeur
     * @param idf
     * @param e
     * @param n
     */
    public Affectation(String idf,Expression e,int n, int num) {
        super(n, num);
        variable = new Idf(idf,n, num);
        exp = e;

    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
        int result = 0;
        HashMap<Entree, Symbole> list = TDS.getInstance().getBlocs().get(0);
        for (Entree et : list.keySet()) {
            if(et.getIdf().toString().equals(variable.toString())){
                result += 1;

            }
        }

        // Test si la variable a été déclarer
        if (result != 0){
            if(!variable.getSymbole().getType().concordance(exp.getType())){
                Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne+" : L'affectation "+ variable.toString() +"=" + exp.toString() +" ne peux pas être effectué, car le type de la variable ("+ variable.toString()+") n'est pas de même type que l'expression (" + exp.toString()+")."));
            }
        }

        if (!exp.estFonction()){
            Symbole symbole =  TDS.getInstance().identifier(new EntreeVariable(variable.toString(), noLigne,numBloc));
        }
        else{
            Symbole symbole =  TDS.getInstance().identifier(new EntreeFonction(exp.getIdf(), noLigne,numBloc));

        }


    }


    /**
     * Fonction toMips, traduction en mips
     * @return le mips en string
     */
    @Override
    public String toMIPS() {
        // On met dans v0
        String code = "";
        code += "# Affectation (" + variable.toString() +" = "+ exp.toString()+")\n"+
                exp.toMIPS()+
                "\tsw $v0, "+ variable.getSymbole().getDeplacement()+"($s7)" +"\n\n";

        return code;
    }

    /**
     * Fonction qui indique si Instruction estRetourne
     * @return
     */
    @Override
    public boolean estRetourner() {
        return false;
    }
}
