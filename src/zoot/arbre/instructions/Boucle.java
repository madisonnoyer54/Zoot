package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.FabriqueNumero;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.TDS;
import zoot.tds.Type;

import java.util.List;

public class Boucle extends Instruction{
    private BlocDInstructions blocDInstructions;
    private Expression e;
    private int numero;
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
        this.numero = FabriqueNumero.getInstance().genererNombre();
    }

    @Override
    public void verifier() {
        // Les Instructions

        for (Instruction instruction : blocDInstructions.getProgramme()) {
            instruction.verifier();

        }

        if(e.getType().equals(Type.ENTIER)){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne+" : l'expression doit etre de type boolean"));

        }





    }

    @Override
    public String toMIPS() {
        StringBuilder sb=new StringBuilder();
        sb.append("Boucle").append(numero).append(":\n");
        sb.append(derouleMips(blocDInstructions));
        sb.append(e.toMIPS());
        sb.append("\n# Initialiser $t8 avec la valeur vrai\n");
        sb.append("\tla $t8, vraiAff\n");
        sb.append("\tbeq $t8, $v0, FinBoucle").append(numero).append("\n");
        sb.append("\tj Boucle").append(numero).append("\n");
        sb.append("FinBoucle").append(numero).append(": \n");
        return sb.toString();

    }

    /**
     * Fonction qui permet d'ecrire le toMips des instruction dans le bloc
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

    @Override
    public boolean estCondition() {
        return false;
    }
}
