package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.tds.*;

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
        boolean retourParam = false;
        boolean retourVariableLocale = false;
        boolean retourVariableMain = false;
        String code = "\t#Retourne\n";
        int nbVar=TDS.getInstance().getTailleZoneVariable(num);
        nbVar = -nbVar;
        int deplacement = 0;
        int deplacementTotal = 0;
        String registre = "$s7";//s7 par défaut
        //int zoneParam = 12+TDS.getInstance().getCompteParam()*4;
        // depiler s7 et ra

        if(!e.estVariable()) {//si c'est une fonction
           code = code + e.toMIPS();
        }
        ArrayList<HashMap<Entree, Symbole>> tds =  TDS.getInstance().getBlocs();
        for (HashMap<Entree, Symbole> map : tds) {
            for (Entree entree : map.keySet()) {
                Symbole symbole = map.get(entree);
                SymboleVariable symboleVariable=null;

                if(entree.getNumBloc()==0){//le main
                    if(Objects.equals(entree.getIdf(), e.getIdf())) {
                        deplacement = TDS.getInstance().getCompteurDeplace();
                        deplacementTotal = deplacement - symbole.getDeplacement();
                        registre = "$s3"; //on va chercher dans s3 et non s7 les variables du main
                    }


                }
                if(entree.getNumBloc()!=0&& !TDS.getInstance().identifier(entree).estFonction()){
                    symboleVariable = (SymboleVariable) TDS.getInstance().identifier(entree);
                    if(entree.getNumBloc()!=0 && symboleVariable.getNumVar()==0){//on retourne une variable locale
                        if(Objects.equals(entree.getIdf(), e.getIdf())) { //TODO:REVOIR APRES AFFECTATION REGLEE
                            deplacement = TDS.getInstance().getCompteurDeplace();
                            deplacementTotal = deplacement - symbole.getDeplacement();
                        }

                    }
                    if(entree.getNumBloc()!=0 && symboleVariable.getNumVar()!=0){//on retourne un parametre
                        if(Objects.equals(entree.getIdf(), e.getIdf())){
                            int zoneParam = 12+TDS.getInstance().getCompteParam()*4;
                            deplacement = symbole.getDeplacement();
                            deplacementTotal = zoneParam + deplacement; ;//TODO:revoir si pas mieux que hardcode 24
                        }
                    }
                }
                else{
                    deplacementTotal = -1;
                }
            }
        }

        //il faut dépiler les paramètres ??
        List listParam = TDS.getInstance().getListParam();
          code = code + "\n# Rangement du résultat de la fonction\n";
          if(deplacementTotal!=-1) {//si on retourne pas directement un entier // booleen
              code = code +
                      "\t lw $v0," + deplacementTotal + "(" + registre + ")\n";
          }
          code = code+
                  "\tsw $v0, 12($s7)\n"+
                  "# Depile des variables\n"+
                "\taddi $sp,$sp, "+nbVar+"\n"+ //OK
                  "# Récupère chaînage dynamique\n"+
                  "\tlw $s7, 4($sp)\n" +
                  "# Dépile chaînage dynamique\n"+
                  "\taddi $sp, $sp, 4\n" + //OK //A AJOUTER AUSSI EN PLUS SI PARAMETRES UNE CASE EN PLUS DANS LA PILE
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

    @Override
    public boolean estBoucleOuCondition() {
        return false;
    }

    @Override
    public Boolean contientRetourner() {
        return null;
    }

    @Override
    public Instruction getRetourner() {
        return null;
    }

    /**
     * Fonction qui indique si Instruction estRetourne (vrai)
     * @return
     */
    public boolean estRetourner(){
        return true;
    }
}
