package zoot.tds;

import zoot.arbre.expressions.Expression;
import zoot.arbre.instructions.Instruction;
import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TDS {
    private int compteurDeplace;
    private static TDS instance = new TDS();
    private ArrayList<HashMap<Entree, Symbole>> blocs;
    private int noActuBloc=0;
    private boolean estDansMain;
    private int compteParam;
    private boolean dansParam;

    private ArrayList<Expression> listParam;
    private  int num;

    private boolean dansAppel;
    private int compteurDeplaceParam;

    /**
     * Constructeur
     */
    public TDS() {
        compteurDeplaceParam=0;
        compteurDeplace =0;
        this.blocs = new ArrayList<>();
        this.blocs.add(new HashMap<>());
        estDansMain = true;
        compteParam = 0;
        dansParam = false;
        num =0;


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
        Boolean result= false;
        // variable existe déjà double déclaration exception!!
        HashMap<Entree,Symbole> list = TDS.getInstance().getBlocs().get(numBloc);
        for (Entree et : list.keySet()) {
            entre2 = et.estFonction();

            // DOuble decla fonction possible si elle ont pas le meme nombre de parametre ( pas de paramettre pour l'instant
            if(et.getIdf().toString().equals(e.idf.toString()) ){

                // Deux variable avec le meme nom
                if(entre1.equals(entre2) && entre1 == false) {
                    Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne()+" et "+et.noLigne +" : Deux variable ne peuvent pas etre déclarer avec le même nom ("+ e.idf.toString()+")."));
                    result = true;
                    // Fonction avec le meme nombre de paramêtre
                }else if (entre1.equals(entre2) && entre1 == true ) {
                    SymboleFonction s1 = (SymboleFonction) s;
                    SymboleFonction s2 = (SymboleFonction) identifier(et);
                    if(s1.getNb() == s2.getNb()){
                        Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne()+" et "+et.noLigne +" : Deux fonction ne peuvent pas etre déclarer avec le même nom si elle ont le même nombre de paramêtre ("+ e.idf.toString()+")."));
                        result = true;
                    }

                }
                // Variable et fonction avec le meme nom
                else {
                    Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne()+" et "+et.noLigne+" : Une variables et une fonction ne peuvent pas etre déclarer avec le même nom ("+ e.idf.toString()+")."));
                    result = true;
                }

            }


        }

        // Si tout vas bien on ajout a la liste
        if(result == false){
            list.put(e, s);
        }
        if(!s.estFonction() && numBloc ==0){

            compteurDeplace-= 4;
            s.setDeplacement(compteurDeplace);
        }
        else{//fonction
            compteurDeplaceParam-=4;
            s.setDeplacement(compteurDeplaceParam);
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
        Symbole symbole = null;

        // On regarde si la variable est déclarer dans son bloc
        HashMap<Entree,Symbole> list2 = TDS.getInstance().getBlocs().get(e.numBloc);
        for (Entree et : list2.keySet()) {

            if(et.getIdf() != null && e.getIdf() != null){
                if(et.getIdf().toString().equals(e.idf.toString())  ){
                    rep = true;
                    symbole = list2.get(et);
                }
            }
            if(et.getIdf() == null || e.getIdf() == null){
                nulle = true;
            }


        }

        // Si elle n'est pas déclarer dans son bloc on regarde dans le main
        if(!rep){
            HashMap<Entree,Symbole> list = TDS.getInstance().getBlocs().get(0);
            for (Entree et : list.keySet()) {

                if(et.getIdf() != null && e.getIdf() != null){
                    if(!e.estFonction() && !et.estFonction()){
                        if(et.getIdf().toString().equals(e.idf.toString())  ){
                            rep = true;
                            symbole = list.get(et);
                        }
                    }else if (e.estFonction() && et.estFonction()){
                        EntreeFonction e1 = (EntreeFonction) et;
                        EntreeFonction e2 = (EntreeFonction) e;
                        if(et.getIdf().toString().equals(e.idf.toString())&& e1.getNbParam() == e2.getNbParam()  ){
                            rep = true;
                            symbole = list.get(et);
                        }
                    }

                }
                if(et.getIdf() == null || e.getIdf() == null){
                    nulle = true;
                }

            }



        }

        if(!rep && nulle == false){
            if(!e.estFonction()){
                Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne() +" : Variable "+ e.getIdf()+ " non déclaré"));

            }else {
                Analyse.getInstance().ajoute(new AnalyseSemantiqueException(e.getNoLigne() +" : Fonction "+ e.getIdf()+ " non déclaré"));

            }
        }
        return symbole;
    }

    /**
     * Fonction qui permet d'indiquer l'entree dans un bloc d'instruction
     */
    public void entreeBloc(){
        if(estDansMain == true){
            this.blocs.add(new HashMap<>());

            this.noActuBloc = this.noActuBloc + 1;
            estDansMain = false;
            compteParam =0;
            dansParam = true;

        }

    }

    public int getCompteParam() {
            return compteParam;

    }

    public int getCompteParam2() {
        if(dansParam){
            return compteParam;
        }
        return 0;
    }

    public void compteParamPlus1(){

        // Si on est dans une fonction on compte les param
        if(estDansMain == false && dansParam == true ){

            compteParam = compteParam +1;
        }

    }

    public void sortieParam(){
        dansParam = false;

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
                Symbole symbole = entry.getValue();
                if(noBloc == entree.getNumBloc()&&!symbole.estFonction()) {
                    SymboleVariable s = (SymboleVariable) TDS.getInstance().identifier(entree);
                    if (s.getNumVar() == 0) { // si c'est le même no de bloc et c'est une variable
                        //System.out.println("pour noBloc : "+noBloc+"variable : " + entree.getIdf());
                        taille++;
                    }
                }
            }
        }
        taille *= -4;
        //System.out.println("\ntest taille zoneVariable pour le nobloc : "+noBloc+" la taille : "+taille);
        return taille;
    }

    public ArrayList<Expression> getListParam() {
        return listParam;
    }


    public void ajouteParamAppel(Expression e){
        if(dansAppel){
            listParam.add(e);
        }

    }

    public void entreeAppel(){

        dansAppel = true;
        listParam = new ArrayList<>();
    }

    public void sortieAppel(){
        dansAppel =false;
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
        if(estDansMain == true){
            return 0;
        }
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

    public int getIdEtiquette(){
        num++;
        return num ;
    }
}
