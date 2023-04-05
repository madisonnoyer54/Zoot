package zoot.arbre.instructions;

import zoot.arbre.expressions.AppelFonction;
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
        Symbole symbole = null;
        int taille;


        if (!exp.estFonction()){
            symbole =  TDS.getInstance().identifier(new EntreeVariable(variable.toString(), noLigne,numBloc));
        }
        else{
            AppelFonction a = (AppelFonction) exp;
            if(a.getListParam() == null){
                taille =0;
            }else{
                taille = a.getListParam().size();
            }
            symbole =  TDS.getInstance().identifier(new EntreeFonction(exp.getIdf(), noLigne,numBloc,taille));

        }

        // Test si la variable a été déclarer

        if (symbole != null){

            if(!variable.getType().concordance(exp.getType()) && ( variable.getNumBloc() == exp.getNumBloc() || variable.getNumBloc() == 0 )){

                Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne+" : L'affectation "+ variable.toString() +"=" + exp.toString() +" ne peux pas être effectué, car le type de la variable ("+ variable.toString()+") n'est pas de même type que l'expression (" + exp.toString()+")."));
            }
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
        if(this.exp.getNumBloc()==0) {
            code += "# Affectation (" + variable.toString() + " = " + exp.toString() + ")\n" + //TODO:A REVOIR LES CONDTIONS
                    exp.toMIPS() +
                    "\tsw $v0, " + variable.getSymbole().getDeplacement() + "($s7)" + "\n\n";
        }
        else{//au niveau de fonction
            int deplacement = -(16 + variable.getSymbole().getDeplacement()); // 16 = case valeur de retour + case adresse retour + case chainage dynamique
            code += exp.toMIPS() +
                    "\tsw $v0, " + deplacement + "($s3)" + "\n\n";
        }

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
