package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.TDS;

public class Condition extends Instruction{
    BlocDInstructions siBloc;
    BlocDInstructions sinonBloc;
    Expression e;
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
                Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne+" : l'expression doit etre de type boolean"));
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
    }

    @Override
    public String toMIPS() {
        int num = TDS.getInstance().getIdEtiquette();
        String res = "#Condition\n";
        res += e.toMIPS();
        res += "beq $v0 $zero sinon" + num + "\n";
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
}
