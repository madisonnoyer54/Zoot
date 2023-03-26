package zoot.arbre;

import zoot.arbre.instructions.Instruction;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.SymboleFonction;
import zoot.tds.TDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<Instruction> programme ;


    /**
     * Constructeur
     * @param n
     */
    public BlocDInstructions(int n, int num) {
        super(n, num) ;
        programme = new ArrayList<>() ;
    }

    public int nbInstruction(){
        return this.programme.size();
    }


    /**
     * Fonction ajouter, elle permet d'ajouter une instruction à la liste
     * @param i
     */
    public void ajouter(Instruction i) {
        programme.add(i) ;
    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
        // Les Instructions
        for (Instruction instruction : programme) {
            instruction.verifier();

        }

        // On verifie les sous programmes pour voir si les fonction on bien un retourne
        HashMap<Entree, Symbole> list = TDS.getInstance().getBlocs().get(0);
        for (Entree et : list.keySet()) {
            if(et.estFonction()){
                SymboleFonction s = (SymboleFonction) list.get(et);

                for (Instruction instruction : s.getBlocDInstructions().programme) {
                    instruction.verifier();

                }

                // sa ne contient pas de retour
                if(!s.getBlocDInstructions().contientRetourner()){
                    Analyse.getInstance().ajoute(new AnalyseSemantiqueException( et.getNoLigne()+" : Le BlocFonction dois contenir un retourne ("+et.getIdf()+")"));

                }
                // Il contient un retourne, On verifie le type
                else{
                    if(!s.getType().concordance(s.getBlocDInstructions().getRetourner().getType())){
                        Analyse.getInstance().ajoute(new AnalyseSemantiqueException(s.getBlocDInstructions().numLigneRetourner()+" : Le retourner de la fonction n'a pas le même type que la déclaration "));
                    }

                }
            }
        }

    }


    /**
     * Fonction toMips, traduction en mips
     * @return le mips en string
     */
    @Override
    public String toMIPS() {
        String code;

        // Début du programme + str( permet le saut de ligne)
        code =
               ".data\n"+
                       "str: 	.asciiz \"\\n\"\n"+
                       "vrai:\t.word 1\n"+
                       "faux:\t.word 0\n"+
                       "vraiAff:\t.asciiz \"vrai\"\n"+
                       "fauxAff:\t.asciiz \"faux\"\n"+
                       ".text\n\n"+
                       "main :\n\n";
        //$s3 à mettre ici ??
        // Les déclaration des variables
        code = code +
                "# Empilement de l'adresse S7\n"+
                "\tmove $s7,$sp\n"+
                "# Reserve la place des variables\n"+
                "\taddi $sp,$sp,"+ TDS.getInstance().getCompteurDeplace()+"\n";
        // Les Instructions
        for (Instruction instruction : programme) {
            code+=instruction.toMIPS();
        }

        // Fin du programme
        code =  code +"\nend :\n" +
                "\tli $v0, 10\n" +
                "\tsyscall";


        //Les déclarations des fonctions
        HashMap<Entree, Symbole> tableFonction = TDS.getInstance().getlistFonction();
        int noBloc = 0;
        for (Entree et : tableFonction.keySet()){
            noBloc++;
            code = code + "\n"+et.getIdf()+":\n"+
                    "\t# Empiler $ra\n"+//à revoir
                    "\tsw $ra, 0($sp) \n"+
                    "\t add $sp, $sp, -4 \n"+
                    "\t# Empiler $s7\n"+
                    "\tsw $s7, 0($sp) \n"+
                    "\t add $sp, $sp, -4 \n"+
                    "\t# Mettre à jour la base locale\n"+
                    "\tmove $s7, $sp\n\n"+
                    "\t# On reserve la place pour les variables\n"+ //condition : si des variable locales sont déclarées
                    "\tadd $sp,$sp,"+TDS.getInstance().getTailleZoneVariable(noBloc)+"\n";


            // Les Instructions de la fonction
            SymboleFonction s = (SymboleFonction) tableFonction.get(et);
            for (Instruction instruction : s.getBlocDInstructions().programme) {
                code = code  + instruction.toMIPS();

            }

        }
        return code;

    }

    public Boolean contientRetourner(){
        boolean finale = false;
        for (Instruction instruction : programme) {
            if(instruction.estRetourner()){
              finale =  true;
            }
        }
        return  finale;
    }

    public Instruction getRetourner(){
        Instruction re = null;
        for (Instruction instruction : programme) {
            if(instruction.estRetourner()){
               re = instruction;
            }
        }
        return re;
    }

    public int numLigneRetourner(){
        int finale = 0;
        for (Instruction instruction : programme) {
            if(instruction.estRetourner()){
                finale =  instruction.noLigne;
            }
        }
        return  finale;
    }
    @Override
    public String toString() {
        return programme.toString() ;
    }

}
