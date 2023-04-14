package zoot.arbre.expressions.unaire;

import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Type;

import java.util.List;

public class Non extends Unaire{
    private int numero;
    /**
     * Constructeur
     *
     * @param n
     * @param num
     */
    public Non(int n, int num,Expression e) {
        super(n, num,e);
        this.numero = FabriqueNumero.getInstance().genererNombre();
    }

    @Override
    public void verifier() {
        e.verifier();
        if(!this.e.estBool() ){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne +" : L'opérateurs de non dois etre un boolean "));

        }
    }

    @Override
    public String toMIPS() {

        StringBuilder code = new StringBuilder();
        code.append(e.toMIPS());
        code.append("\n# Initialiser $t8 avec la valeur faux\n");
        code.append("\tla $t8, faux\n");
        code.append("\tbeq $t8, $v0, Sinon").append(numero).append("\n");
        code.append("\tla $v0, faux\n");
        code.append("\tb FinSi").append(numero).append("\n");
        code.append("Sinon").append(numero).append(":").append("\n");
        code.append("\tla $v0, vrai\n");
        code.append("FinSi").append(numero).append(":\n");
        return code.toString();
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
    public String toString() {
        return  "non"+e;
    }
}
