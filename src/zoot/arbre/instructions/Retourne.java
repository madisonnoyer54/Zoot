package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.tds.TDS;
import zoot.tds.Type;

import java.util.List;

public class Retourne extends Instruction{
    private Expression e;
    private int num;

    /**
     * Constructeur
     *
     * @param n
     */
    public Retourne(Expression e, int n, int num) {
        super(n, num);
        this.e = e;
        this.num=num;

    }

    @Override
    public void verifier() {
        e.verifier();

    }


    @Override
    public String toMIPS() {
        String code = "\t#Retourne\n";
        int nbVar=TDS.getInstance().getTailleZoneVariable(num);
        nbVar = -nbVar;
        // depiler s7 et ra

        if(!e.estVariable()) {
           code = code + e.toMIPS();
        }
        //il faut dépiler les paramètres ??
        List listParam = TDS.getInstance().getListParam();
          code = code + "\n# Depile de s7 et ra\n"+
                "\taddi $sp,$sp, "+nbVar+"\n"+ //OK
                "\taddi $sp, $sp, 4\n" + //OK //A AJOUTER AUSSI EN PLUS SI PARAMETRES UNE CASE EN PLUS DANS LA PILE
                "\taddi $sp, $sp, 4\n" + //OK
                "\tlw $ra, 0($sp)"+ //OK


        "\t#Retour de fonction "+ e.toString()    +
        //"\n\tsw $v0, 4($sp)\n"+ // 12 et pas 0 probleme ici!
        "\t\njr $ra\n";
        return code;
    }

    /**
     * Fonction qui donne le type de l'expression
     * @return
     */
    public Type getType(){
        return e.getType();
    }
    /**
     * Fonction qui indique si Instruction estRetourne (vrai)
     * @return
     */
    public boolean estRetourner(){
        return true;
    }
}
