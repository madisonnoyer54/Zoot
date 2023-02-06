package zoot.exceptions;

import zoot.arbre.FabriqueNumero;
import zoot.arbre.expressions.Idf;

import java.util.ArrayList;

public class Analyse {

    private ArrayList<AnalyseSemantiqueException> list;
    private ArrayList<String> listVariable;
    private static final Analyse instance = new Analyse();
    public Analyse() {
        list = new ArrayList<>();
        listVariable = new ArrayList<>();
    }

    /**
     * Fonction qui retourne une instance de Analyse
     *
     * @return une instance de Analyse
     */
    public static Analyse getInstance() {
        return instance;
    }

    public void ajoute(AnalyseSemantiqueException e){
        list.add(e);
    }

    public void ajouteIDF(String i){
        int result = 0;

        for(int j =0; j < listVariable.size(); j++){
             if(listVariable.get(j).toString().equals(i.toString())){
                 result = 1;
             }
        }
        if(result == 0){
            listVariable.add(i);
        }
    }

    public ArrayList<AnalyseSemantiqueException> getList() {
        return list;
    }

    public void setList(ArrayList<AnalyseSemantiqueException> list) {
        this.list = list;
    }

    public ArrayList<String> getListVariable() {
        return listVariable;
    }

    public void setListVariable(ArrayList<String> listVariable) {
        this.listVariable = listVariable;
    }
}
