package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;
import zoot.tds.TDS;

import java.util.List;

public class Boucle extends Instruction{
    private BlocDInstructions blocDInstructions;
    private Expression e;
    /**
     * Constructeur
     *
     * @param n
     * @param numBloc
     */
    public Boucle(int n, int numBloc, ArbreAbstrait a, Expression e) {
        super(n, numBloc);
        this.e = e;
        blocDInstructions = (BlocDInstructions) a;

    }

    @Override
    public void verifier() {
        this.e.verifier();
        if(!(this.e.estBool() && this.e != null)){
       //     Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne+" : l'expression doit etre de type boolean"));

        }
        // Les Instructions
        for (Instruction instruction : blocDInstructions.getProgramme()) {
            instruction.verifier();

        }
    }

    @Override
    public String toMIPS(List<String> registres) {
        int num = TDS.getInstance().getIdEtiquette();
        String res = e.toMIPS(registres);
        res += "#Boucle\n";
        res += "j condition" + num + "\n";
        res += "loop" + num + ":\n";
        res += derouleMips(blocDInstructions);

        res += e.toMIPS(registres);
        res += "condition" + num + ":";
        res += "bne $v0 $zero loop" + num + "\n";

        return res;
    }

    public String derouleMips(BlocDInstructions b){
        String code = "";
        for (Instruction instruction : b.getProgramme()) {
          //  code+=instruction.toMIPS(registres);
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
        for (Instruction instruction : blocDInstructions.getProgramme()) {
            if(instruction.estRetourner()){
                finale =  true;
            }
            if(instruction.estBoucleOuCondition()){
                instruction.contientRetourner();
            }
        }

        return  finale;
    }

    @Override
    public Instruction getRetourner() {
        Instruction finale = null;

        for (Instruction instruction :blocDInstructions.getProgramme()) {
            if (instruction.estRetourner()) {
                finale = instruction;
            }
            if (instruction.estBoucleOuCondition()) {
                finale = instruction.getRetourner();
            }
        }

        return  finale;
    }
}
