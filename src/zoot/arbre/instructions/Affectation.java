package zoot.arbre.instructions;

import zoot.arbre.expressions.AppelFonction;
import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Affectation extends Instruction{
    protected Expression exp ;
    protected Idf variable;
    private int n;


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
        this.n = num;

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
        //SymboleVariable symbole = (SymboleVariable)  TDS.getInstance().identifier(new EntreeVariable(variable.toString(), noLigne,numBloc));

        ArrayList<HashMap<Entree, Symbole>> tds =  TDS.getInstance().getBlocs();
        for (HashMap<Entree, Symbole> map : tds) {
            for (Entree entree : map.keySet()) {
                Symbole symbole = map.get(entree);
                SymboleVariable symboleVariable = null;

                if (entree.getNumBloc() == 0) {//le main
                    if(Objects.equals(entree.getIdf(), variable.getIdf())) {
                        System.out.println(variable.getIdf());
                        int deplacement = TDS.getInstance().getCompteurDeplace();
                        int deplacementTotal = deplacement - variable.getSymbole().getDeplacement();
                        //int deplacement = -(16 + variable.getSymbole().getDeplacement()); // 16 = case valeur de retour + case adresse retour + case chainage dynamique
                        code += "# Affectation (" + variable.toString() + " = " + exp.toString() + ")\n" +
                                exp.toMIPS() +
                                "\tsw $v0, " + deplacementTotal + "($s3)" + "\n\n";
                    }
                }
                if(entree.getNumBloc()!=0&& !TDS.getInstance().identifier(entree).estFonction()){
                    symboleVariable = (SymboleVariable) TDS.getInstance().identifier(entree);
                    if(entree.getNumBloc()!=0 && symboleVariable.getNumVar()==0){//on retourne une variable locale
                        if(Objects.equals(entree.getIdf(), variable.getIdf())) { //TODO:REVOIR APRES AFFECTATION REGLEE
                            int deplacement = TDS.getInstance().getCompteurDeplace();
                            int deplacementTotal = deplacement - symbole.getDeplacement();
                            code += "# Affectation (" + variable.toString() + " = " + exp.toString() + ")\n" + //TODO:A REVOIR LES CONDTIONS
                                    exp.toMIPS() +
                                    "\tsw $v0, " + deplacementTotal + "($s7)" + "\n\n";
                        }

                    }
                    if(entree.getNumBloc()!=0 && symboleVariable.getNumVar()!=0){//on retourne un parametre
                        if(Objects.equals(entree.getIdf(), variable.getIdf())){
                            int deplacement = TDS.getInstance().getCompteurDeplace();
                            int deplacementTotal = 24 + deplacement ;//TODO:revoir si pas mieux que hardcode 24
                            code += "# Affectation (" + variable.toString() + " = " + exp.toString() + ")\n" + //TODO:A REVOIR LES CONDTIONS
                                    exp.toMIPS() +
                                    "\tsw $v0, " +deplacementTotal + "($s7)" + "\n\n";
                        }
                    }
                }
            }
        }
        if(true) {//TODO: Revoir les IF : Si déclarer dans le main : s3 // si déclarer dans la fonction : s7
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
