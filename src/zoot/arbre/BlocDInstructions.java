package zoot.arbre;

import zoot.arbre.instructions.Instruction;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.*;

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
                "# Empilement de l'adresse S3\n"+
                "\tmove $s3,$sp\n"+
                "# Allocation de la place pour la valeur de retour\n"+
                "\taddi $sp, $sp, -4\n"+
                "# Allocation de la place pour l'adresse de retour\n"+
                "\taddi $sp, $sp, -4\n"+
                "# Chaînage dynamique\n"+
                "\taddi $sp, $sp, -4\n"+
                "# Empilement de l'adresse S7\n"+
                "\tmove $s7,$sp\n"+
                "# Reserve la place des variables locales du main\n"+
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
        for (Entree et : tableFonction.keySet()){
            EntreeFonction e1 = (EntreeFonction) et;
            // etiquette
            code = code + "\n"+et.getIdf()+ e1.getNbParam()+":\n"+ //OK
                    // Empile l'adresse de retour
                    "\t# Empiler $ra\n"+
                    "\t addi $sp, $sp, -4 \n"+
                    "\tsw $ra, 4($sp) \n"+
                    //Empiler le chainage dynamique
                    "\t# Empiler $s7\n"+
                    "\t addi $sp, $sp, -4 \n"+
                    "\tsw $s7, 4($sp) \n"+
                    "\t# Mettre à jour la base locale\n"+
                    "\tmove $s7, $sp\n\n"+

                    // reserver de la place variables
                    "\t# On reserve la place pour les variables\n"; //condition : si des variable locales sont déclarées sinon ici 0
                    code = code + "\taddi $sp,$sp," + TDS.getInstance().getTailleZoneVariable(et.getNumBloc()) + "\n";


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
            if(instruction.estBoucleOuCondition()){
                finale = instruction.contientRetourner();
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
            if(instruction.estBoucleOuCondition()){
                re = instruction.getRetourner();
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

    public ArrayList<Instruction> getProgramme() {
        return programme;
    }
}
