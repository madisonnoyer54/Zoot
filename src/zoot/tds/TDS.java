package zoot.tds;

import zoot.exceptions.AnalyseVariableNomDejaPris;

import java.util.HashMap;

public class TDS {
    private int compteurDeplace;
    private HashMap<Entree, Symbole> tableDesSymboles;
    private static TDS instance = new TDS();


    /**
     * Constructeur
     */
    public TDS() {
        this.tableDesSymboles = new HashMap<>();
        compteurDeplace =0;
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
        // variable existe déjà double déclaration exception!!
        int result = 0;
        HashMap<Entree,Symbole> list = TDS.getInstance().getTableDesSymboles();
        for (Entree et : list.keySet()) {
            if(et.getIdf().toString().equals(e.idf.toString())){
                throw new AnalyseVariableNomDejaPris(" Deux variable ne peuvent pas etre déclarer avec le même nom "+ e.idf.toString());

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
        //à générer exception variable non déclarée
        if (!this.tableDesSymboles.containsKey(e)) {
        }
        return this.tableDesSymboles.get(e);
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
}
