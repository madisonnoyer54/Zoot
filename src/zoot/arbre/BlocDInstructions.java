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
    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
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

        // On verifie les sous programmes
        HashMap<Entree, Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
           if(et.estFonction()){
               SymboleFonction s = (SymboleFonction) list.get(et);

               for (Instruction instruction : s.getBlocDInstructions().programme) {
                   instruction.verifier();

               }

               if(!s.getBlocDInstructions().contientRetourner()){
                   Analyse.getInstance().ajoute(new AnalyseSemantiqueException( et.getNoLigne()+" : Le BlocFonction dois contenir un retourne ("+et.getIdf()+")"));

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

        // Les déclaration des variables
        code = code +
                "# Initialiser $s7 avec $sp\n"+
                "\tmove $s7,$sp\n"+
                "# Reserve la place des variables\n"+
                "\taddi $sp,$sp,"+ TDS.getInstance().getTailleZoneVariable()*(-4)+ "\n\n";

        // Les Instructions
        for (Instruction instruction : programme) {
            code+=instruction.toMIPS();
        }

        // Fin du programme
        code =  code +"\nend :\n" +
                "\tli $v0, 10\n" +
                "\tsyscall";
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
