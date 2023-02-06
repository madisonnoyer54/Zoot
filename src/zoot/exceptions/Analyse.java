package zoot.exceptions;

import zoot.arbre.FabriqueNumero;

import java.util.ArrayList;

public class Analyse {

    private ArrayList<AnalyseSemantiqueException> list;
    private static final Analyse instance = new Analyse();
    public Analyse() {
        list = new ArrayList<>();
    }

    /**
     * Fonction qui retourne une instance de Analyse
     *
     * @return une instance de la fabrique
     */
    public static Analyse getInstance() {
        return instance;
    }

    public void ajoute(AnalyseSemantiqueException e){
        list.add(e);
    }

    public ArrayList<AnalyseSemantiqueException> getList() {
        return list;
    }

    public void setList(ArrayList<AnalyseSemantiqueException> list) {
        this.list = list;
    }
}
