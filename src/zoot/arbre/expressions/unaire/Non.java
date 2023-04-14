package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Type;

import java.util.List;

public class Non extends Unaire{
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    public Non(int n, int num,Expression e) {
        super(n, num,e);
    }

    @Override
    public void verifier() {
        e.verifier();
        if(!this.e.estBool() ){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne +" : L'opérateurs de non dois etre un boolean "));

        }
    }

    @Override
    public Type getType() {
        return Type.BOOLEEN;
    }

    @Override
    public boolean estBool() {
        return false;
    }

    @Override
    public boolean estFonction() {
        return false;
    }

    @Override
    public String getIdf() {
        return null;
    }

    @Override
    public boolean estConstante() {
        return false;
    }

    @Override
    public int getNombreDePlaces() {
        return 0;
    }

    @Override
    public String toMIPS() {
        return null;
    }



    @Override
    public String toString() {
        return  "non"+e;
    }
}
