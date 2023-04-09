package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;

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
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne+" : l'expression doit etre de type boolean"));

        }
        blocDInstructions.verifier();
    }

    @Override
    public String toMIPS() {
        return null;
    }

    @Override
    public boolean estRetourner() {
        return false;
    }
}
