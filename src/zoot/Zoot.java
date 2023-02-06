package zoot;

import zoot.analyse.AnalyseurLexical;
import zoot.analyse.AnalyseurSyntaxique;
import zoot.arbre.ArbreAbstrait;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;


public class Zoot {


    /**
     * Constructeur
     * @param nomFichier
     */
    public Zoot(String nomFichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;

            arbre.verifier() ;
            if(!Analyse.getInstance().getList().isEmpty() ){
                String m="\n";


                for (int i = 0; i < Analyse.getInstance().getList().size() ; i++){
                     m = m  + Analyse.getInstance().getList().get(i).getMessage() + "\n\n";

                }

                System.out.println(m);
                exit (0);
            }
            System.out.println("COMPILATION OK") ;

            String nomSortie = nomFichier.replaceAll("[.]zoot", ".mips") ;
            PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie))) ;
            flot.println(arbre.toMIPS());
            flot.close() ;
        }
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Zoot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar zoot.jar <fichierSource.zoot>") ;
            exit(1) ;
        }
        new Zoot(args[0]) ;

    }

}
