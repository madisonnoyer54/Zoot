package zoot.exceptions;



import java.util.ArrayList;

public class Analyse {

    private ArrayList<AnalyseSemantiqueException> list;
    private static final Analyse instance = new Analyse();


    /**
     * Constructeur
     */
    public Analyse() {
        list = new ArrayList<>();
    }


    /**
     * Fonction qui retourne une instance de Analyse
     *
     * @return une instance de Analyse
     */
    public static Analyse getInstance() {
        return instance;
    }


    /**
     * Fonction qui permet d'ajouter a la liste
     * @param e
     */
    public void ajoute(AnalyseSemantiqueException e){
        list.add(e);
    }


    /**
     * Getteur
     * @return la list
     */
    public ArrayList<AnalyseSemantiqueException> getList() {
        return list;
    }



}
