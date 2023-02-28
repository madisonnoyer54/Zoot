package zoot.tds;

import zoot.arbre.instructions.Instruction;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TDS {
    private int compteurDeplace;
    private HashMap<Entree, Symbole> tableDesSymboles;
    private static TDS instance = new TDS();
    private List<HashMap<Entree, Symbole>> blocs;
    private int noActuBloc = 0;
    private int noPrecBloc = 0;


    /**
     * Constructeur
     */
    public TDS() {
        this.tableDesSymboles = new HashMap<>();
        compteurDeplace =0;
        this.blocs = new ArrayList<>();
        this.blocs.add(new HashMap<>());
    }


    /**
     * Getteur
     * @return instance
     */
    public static TDS getInstance() {
        return instance;
    }


    /**
     * Fonction ajouter, elle permet d'ajouter à la table des symboles si l'entrée n'est pas déja prise.
     * @param e
     * @param s
     */
    public void ajouter (Entree e, Symbole s){
        Boolean entre1 = e.estFonction();
        Boolean entre2 = null;
        // variable existe déjà double déclaration exception!!
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            entre2 = et.estFonction();

                // DOuble decla fonction possible si elle ont pas le meme nombre de parametre ( pas de paramettre pour l'instant
                if(et.getIdf().toString().equals(e.idf.toString()) ){

                    // Deux variable avec le meme nom
                    if(entre1.equals(entre2) && entre1 == false) {
                        Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne()+" et "+et.noLigne +" : Deux variable ne peuvent pas etre déclarer avec le même nom ("+ e.idf.toString()+")."));

                        // Fonction avec le meme nombre de paramêtre
                    }else if (entre1.equals(entre2) && entre1 == true) {
                        Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne()+" et "+et.noLigne +" : Deux fonction ne peuvent pas etre déclarer avec le même nom si elle ont le même nombre de paramêtre ("+ e.idf.toString()+")."));

                    }
                    // Variable et fonction avec le meme nom
                    else {
                        Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne()+" et "+et.noLigne+" : Une variables et une fonction ne peuvent pas etre déclarer avec le même nom ("+ e.idf.toString()+")."));

                    }

                }


        }

        this.tableDesSymboles.put(e, s);
        s.setDeplacement(compteurDeplace);
        compteurDeplace-= 4;
    }


    /**
     * Fonction identifier, elle permet d'identifier un symbole à partir de son entré
     * @param e
     * @return symbole
     */
    public Symbole identifier(Entree e){
        // Vérifie que la variable à étais déclarer sa a été verifier dans IDF
        boolean rep = false;
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            if(et.getIdf().toString().equals(e.idf.toString())  ){
                rep = true;
            }
        }
        if(!rep){
            if(!e.estFonction()){
                Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne() +" : Variable "+ e.getIdf()+ " non déclaré"));

            }else {
                Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne() +" : Fonction "+ e.getIdf()+ " non déclaré"));

            }
        }

        return this.tableDesSymboles.get(e);
    }

    /**
     * Fonction qui permet d'indiquer l'entree dans un bloc d'instruction
     */
    public void entreeBloc(){
        if (noActuBloc == 0) {
            this.blocs.add(new HashMap<>());
        }
        this.noActuBloc = this.blocs.size() - 1;
    }

    /**
     * Fonction qui permet d'indiquer la sortie d'un bloc d'instruction
     */
    public void sortieBloc(){
        this.noActuBloc = 0;
     }

    /**
     * Getteur
     * @return taille de la list
     */
    public int getTailleZoneVariable(){
        return tableDesSymboles.size();
    }


    /**
     * Getteur
     * @return compteur deplacement
     */
    public int getCompteurDeplace() {
        return compteurDeplace;
    }


    /**
     * Setteur
     * @param compteurDeplace
     */
    public void setCompteurDeplace(int compteurDeplace) {
        this.compteurDeplace = compteurDeplace;
    }


    /**
     * Getteur
     * @return Hashmap table des symboles
     */
    public HashMap<Entree, Symbole> getTableDesSymboles() {
        return tableDesSymboles;
    }


    /**
     * Setteur
     * @param tableDesSymboles
     */
    public void setTableDesSymboles(HashMap<Entree, Symbole> tableDesSymboles) {
        this.tableDesSymboles = tableDesSymboles;
    }


    /**
     * Fonction qui permet de recumperais la liste des fonctions
     * @return
     */
    public HashMap<Entree,Symbole> getlistFonction(){

       HashMap<Entree, Symbole> tableFonction = new HashMap<>();


        for (Entree et : tableDesSymboles.keySet()) {
            if(et.estFonction()){
                SymboleFonction s = (SymboleFonction) tableDesSymboles.get(et);

              tableFonction.put(et, s);
            }
        }
        return tableFonction;
    }
}
