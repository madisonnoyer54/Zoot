package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;

public class Affectation extends Instruction{
    protected Expression exp ;
    protected Idf variable;


    /**
     * Constructeur
     * @param idf
     * @param e
     * @param n
     */
    public Affectation(String idf,Expression e,int n) {
        super(n);
        variable = new Idf(idf,n);
        exp = e;
    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
        /*
        // Vérifie que la variable à étais initialiser
        boolean rep = false;
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            if(et.getIdf().toString().equals(variable.toString())){
                rep = true;
            }
        }
       if(!rep){
           throw new AnalyseVariableNonDeclare("La variable dans l'Affectation "+ variable.toString()+ " = "+ exp.toString()+ " n'a pas été déclaré");
       }

         */

        // Vérifie que le coter gauche et droit sont du même type
        if(!variable.getSymbole().getType().concordance(exp.getType())){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException("Le type de la variable "+ variable.toString()+" n'est pas de même type de l'expression voulant étre affecter"));
        }
    }


    /**
     * Fonction toMips, traduction en mips
     * @return
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
}
