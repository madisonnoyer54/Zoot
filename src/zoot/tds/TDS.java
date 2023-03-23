package zoot.tds;

import zoot.arbre.instructions.Instruction;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TDS {
    private int compteurDeplace;
    private ArrayList compteur;
    //private HashMap<Entree, Symbole> tableDesSymboles;
    private static TDS instance = new TDS();
    private ArrayList<HashMap<Entree, Symbole>> blocs;
    private int noActuBloc=0;
    private boolean estDansMain;


    /**
     * Constructeur
     */
    public TDS() {
        //this.tableDesSymboles = new HashMap<>();
        compteurDeplace =0;
        this.blocs = new ArrayList<>();
        this.blocs.add(new HashMap<>());
        estDansMain = true;
      //  compteur = new ArrayList<>();
     //   compteur.set(0,0);
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
    public void ajouter (Entree e, Symbole s,int numBloc){
        Boolean entre1 = e.estFonction();
        Boolean entre2 = null;
        System.out.println(numBloc);
        System.out.println(e.idf);
        System.out.println(noActuBloc);
        System.out.println(" ");

        // variable existe déjà double déclaration exception!!
        HashMap<Entree,Symbole> list = TDS.getInstance().getBlocs().get(numBloc);
        for (Entree et : list.keySet()) {
            entre2 = et.estFonction();

                // Double decla fonction possible si elle ont pas le meme nombre de parametre ( pas de paramettre pour l'instant
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
        list.put(e, s);
        if(!s.estFonction() && numBloc ==0){
         //   compteur.set( compteur.get(numBloc) - 4);
          //  compteurDeplace-= 4;
           // s.setDeplacement(compteurDeplace);
        }

    }


    /**
     * Fonction identifier, elle permet d'identifier un symbole à partir de son entré
     * @param e
     * @return symbole
     */
    public Symbole identifier(Entree e){
        // Vérifie que la variable à étais déclarer sa a été verifier dans IDF
        boolean nulle = false;
        boolean rep = false;

        // Déclarer dans son bloc
        HashMap<Entree,Symbole> list = TDS.getInstance().getBlocs().get(e.numBloc);
        for (Entree et : list.keySet()) {
            if(et.getIdf() != null && e.getIdf() != null){
                if(et.getIdf().toString().equals(e.idf.toString())  ){
                    rep = true;
                }
            }
            if(et.getIdf() == null || e.getIdf() == null){
                nulle = true;
            }

        }

        // Déclarer dans le main
        HashMap<Entree,Symbole> list2 = TDS.getInstance().getBlocs().get(0);
        for (Entree et : list2.keySet()) {
            if(et.getIdf() != null && e.getIdf() != null){
                if(et.getIdf().toString().equals(e.idf.toString())  ){
                    rep = true;
                }
            }
            if(et.getIdf() == null || e.getIdf() == null){
                nulle = true;
            }

        }

        if(!rep && nulle == false){
            if(!e.estFonction()){
                Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne() +" : Variable "+ e.getIdf()+ " non déclaré"));

            }else {
                Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne() +" : Fonction "+ e.getIdf()+ " non déclaré"));

            }
        }

        return list.get(e);
    }

    /**
     * Fonction qui permet d'indiquer l'entree dans un bloc d'instruction
     */
    public void entreeBloc(){
        if(estDansMain == true){
            System.out.println("cc");
            this.blocs.add(new HashMap<>());

            this.noActuBloc = this.noActuBloc + 1;
            estDansMain = false;
            compteur.set(noActuBloc,0);
        }

    }

    /**
     * Fonction qui permet d'indiquer la sortie d'un bloc d'instruction
     */
    public void sortieBloc(){
       // this.noActuBloc = 0;
        estDansMain = true;
     }

    /**
     * Getteur
     * @return taille de la list
     */
    public int getTailleZoneVariable(int noBloc){
        int taille = 0;
        for (HashMap<Entree, Symbole> hm : blocs ) {
            for (Map.Entry<Entree, Symbole> entry : hm.entrySet()) {
                Entree entree = entry.getKey();
                if (noBloc == entree.getNumBloc()) {
                    //System.out.println(numR);
                    taille++;
                }
            }
        }
        taille *= -4;
        return taille;
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
     * Fonction qui permet de recuperer la liste des fonctions
     * @return
     */
    public HashMap<Entree,Symbole> getlistFonction(){

       HashMap<Entree, Symbole> tableFonction = new HashMap<>();


        for (Entree et : blocs.get(0).keySet()) {
            if(et.estFonction()){
                SymboleFonction s = (SymboleFonction) blocs.get(0).get(et);

              tableFonction.put(et, s);
            }
        }
        return tableFonction;
    }

    public int getNoActuBloc() {
        return noActuBloc;
    }

    public ArrayList<HashMap<Entree, Symbole>> getBlocs() {
        return blocs;
    }

    public void setBlocs(ArrayList<HashMap<Entree, Symbole>> blocs) {
        this.blocs = blocs;
    }

    public void setNoActuBloc(int noActuBloc) {
        this.noActuBloc = noActuBloc;
    }
}
