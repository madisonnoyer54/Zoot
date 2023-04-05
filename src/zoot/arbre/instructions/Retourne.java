package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;
import zoot.tds.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
        int deplacement = 0;
        int deplacementParam = 0;
        //int zoneParam = 12+TDS.getInstance().getCompteParam()*4;
        // depiler s7 et ra

        if(!e.estVariable()) {
           code = code + e.toMIPS();
        }
        ArrayList<HashMap<Entree, Symbole>> tds =  TDS.getInstance().getBlocs();
        for (HashMap<Entree, Symbole> map : tds) {
            for (Entree entree : map.keySet()) {
                Symbole symbole = map.get(entree);
                if(Objects.equals(entree.getIdf(), e.getIdf())){
                    deplacement = symbole.getDeplacement();
                    deplacementParam = 24 + deplacement ;
                }
            }
        }

        //il faut dépiler les paramètres ??
        List listParam = TDS.getInstance().getListParam();
          code = code + "\n# Rangement du résultat de la fonction\n"+
                  "\t lw $v0,"+deplacementParam+"($s7)\n"+
                  "\tsw $v0, 12($s7)\n"+
                  "# Depile des variables\n"+
                "\taddi $sp,$sp, "+nbVar+"\n"+ //OK
                  "# Récupère chaînage dynamique\n"+
                  "\tlw $s7, 4($sp)\n" +
                  "# Dépile chaînage dynamique\n"+
                  "\taddi $sp, $sp, 4\n" + //OK //A AJOUTER AUSSI EN PLUS SI PARAMETRES UNE CASE EN PLUS DANS LA PILE
                  "# Récupère adresse de retour\n"+
                  "\tlw $ra, 4($sp)\n" +
                  "# Dépile adresse de retour\n"+
                "\taddi $sp, $sp, 4\n" + //OK
                //"\tlw $ra, 0($sp)"+ //OK


        "\t#Retour de fonction "+ e.toString()    +
        //"\n\tsw $v0, 4($sp)\n"+ // 12 et pas 0 probleme ici!
        "\n\tjr $ra\n";
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
