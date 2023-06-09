package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.binaire.Binaire;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.TDS;
import zoot.tds.Type;

import java.util.List;

public class Condition extends Instruction{
    private BlocDInstructions siBloc;
    private BlocDInstructions sinonBloc;
    private Expression e;
    /**
     * Constructeur
     *
     * @param n
     * @param numBloc
     */
    public Condition(int n, int numBloc, Expression e, ArbreAbstrait a, ArbreAbstrait a1) {
        super(n, numBloc);
        siBloc = (BlocDInstructions) a;
        sinonBloc = (BlocDInstructions) a1;
        this.e = e;
    }

    @Override
    public void verifier() {
        if(e!=null){
            e.verifier();

            if(!e.estBool()){
               // Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne+" : l'expression doit etre de type boolean"));
            }
            else{
                if(siBloc!= null){
                    siBloc.verifier();
                }
                if(sinonBloc!= null){
                    sinonBloc.verifier();
                }
            }
        }

        if(e.getType().equals(Type.ENTIER)){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne+" : l'expression doit etre de type boolean"));

        }
    }

    @Override
    public String toMIPS() {
        int num = TDS.getInstance().getIdEtiquette();
        String res = "#Condition\n";
        res += e.toMIPS();
        res += "beqz $v0, sinon" + num + "\n";
        if (siBloc != null)
            res += derouleMips(siBloc);
        res += "j finsi" + num + "\n";
        res += "sinon" + num + ":\n";
        if(sinonBloc!= null) {
            res += derouleMips(sinonBloc);
        }
        res += "finsi" + num + ":\n";
        return res;
    }

    /**
     * Fonction qui permet de derouler le mips des instruction
     * @param b le bloc
     * @return
     */
    public String derouleMips(BlocDInstructions b){
        String code = "";
        for (Instruction instruction : b.getProgramme()) {
            code+=instruction.toMIPS();
        }
        return code;
    }

    @Override
    public boolean estRetourner() {
        return false;
    }

    @Override
    public boolean estBoucleOuCondition() {
        return true;
    }

    @Override
    public Boolean contientRetourner(){
        boolean finale = false;
        boolean finale2 = false;
        if(siBloc!= null){
            for (Instruction instruction : siBloc.getProgramme()) {
                if(instruction.estRetourner()){
                    finale =  true;
                }
                if(instruction.estBoucleOuCondition()){
                    instruction.contientRetourner();
                }
            }
        }
        if(sinonBloc != null){
            for (Instruction instruction : sinonBloc.getProgramme()) {
                if (instruction.estRetourner()) {
                    finale2 = true;
                }
                if (instruction.estBoucleOuCondition()) {
                    instruction.contientRetourner();
                }
            }
        }

        if(finale == true && finale2 == true){
            finale =true;
        }else{
            finale2 = false;
        }
        return  finale;
    }

    @Override
    public Instruction getRetourner() {
       Instruction finale = null;
        for (Instruction instruction : siBloc.getProgramme()) {
            if(instruction.estRetourner()){
                finale =  instruction;
            }
            if(instruction.estBoucleOuCondition()){
                instruction.contientRetourner();
            }
        }
        for (Instruction instruction : sinonBloc.getProgramme()) {
            if (instruction.estRetourner()) {
                finale = instruction;
            }
            if (instruction.estBoucleOuCondition()) {
               finale = instruction.getRetourner();
            }
        }

        return  finale;
    }

    @Override
    public boolean estCondition() {
        return true;
    }

    /**
     * Gettuer
     * @return le bloc si
     */
    public BlocDInstructions getSiBloc() {
        return siBloc;
    }

}
