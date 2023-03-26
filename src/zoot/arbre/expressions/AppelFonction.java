package zoot.arbre.expressions;

import zoot.exceptions.Analyse;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.*;

import java.util.ArrayList;
import java.util.HashMap;

public class AppelFonction extends Expression{
    private String idf;
    private int n;
    private ArrayList<Expression> listParam;


    /**
     * Constructeur
     * @param idf
     * @param n
     */
    public AppelFonction(String idf,int n, int num, ArrayList<Expression> list) {
        super(n,num);
        this.idf = idf;
        this.n=n;
        listParam = list;
        verifier();
    }

    /**
     * Fonction vérifier de AppelFonction
     */
    @Override
    public void verifier() {
        boolean result = false;
        Entree e = null;
        Symbole sy= null;
        int compte= 0;
        int compteRes =0;
        boolean result2= false;
        boolean result3 = false;


        // Verif si les param entrer son declarer
        for (int i = 0; i < listParam.size(); i++) {
            Symbole symbole =  TDS.getInstance().identifier(new EntreeVariable(listParam.get(i).getIdf(), noLigne,numBloc));
            if(!listParam.get(i).estConstante() && symbole == null){
                result3 = true;
            }


        }

        // Test si l'appel correspond a une declaration de fonction
        HashMap<Entree,Symbole> list = TDS.getInstance().getlistFonction();
        for (Entree et : list.keySet()) {
            compte = compte+1;
            SymboleFonction s = (SymboleFonction) TDS.getInstance().identifier(et);
            if(et.getIdf().toString().equals(idf.toString()) && listParam.size() == s.getNb()){
                result = true;
                sy =s;
                e = et;
                compteRes =compte;
            }
        }
       // System.out.println("nombloc"+compteRes);

        if(result == false){
            Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne +" : L'appel de fonction " + this.toString() + " n'appartient à aucune déclaration"));

        }else{// Verif si les param sont de meme type

            if(result3 == false){
                HashMap<Entree,Symbole> list2 = TDS.getInstance().getBlocs().get(compteRes);
                for (Entree et : list2.keySet()) {
                    SymboleVariable s = (SymboleVariable) TDS.getInstance().identifier(et);
                    if(s.getNumVar() != 0  ){
                        if(listParam.get(s.getNumVar()-1).getType() != s.getType()) {
                            result2 =true;
                        }
                    }

                }
                if(result2 ==true){
                    Analyse.getInstance().ajoute(new AnalyseSemantiqueException(noLigne + " : L'appel de fonction " + this.toString() + " n'a pas le meme type de param que la déclaration de fonction"));

                }
            }

        }
    }

    /**
     * Fonction toMips qui génère le code mips à chaque appel de fonction
     * @return String code mips
     */
    @Override
    public String toMIPS() {
        String code;
        ArrayList<Expression> listParam = TDS.getInstance().getListParam();
        code = "\t# Appel de la fonction "+ idf +
                "\n\tadd $sp, $sp, -4\n"+
                "\t# Réserve la place pour le résultat de la fonction\n";
                for(Expression e : listParam){
                    code = e.toMIPS()+
                    "\n\tsw $v0, ($sp) \n"+
                    "\tadd $sp, $sp,-4 \n";
                }
                code = code +
                "\tjal "+idf+"\n"+

                // On depile et on met dans S7
                "\tadd $sp, $sp, 4 # Depile\n"+
                "\tsw $v0, 0($sp) # On met dans v0\n";



        return code ;
    }

    /**
     * Getteur
     * @return le symbole
     */
    public Symbole getSymbole() {

        Symbole symbole = null;
        // 0 car la fonction est forcement déclarer dans le main
        HashMap<Entree,Symbole> list = TDS.getInstance().getBlocs().get(0);
        for (Entree et : list.keySet()) {
            if(et.getIdf().equals(idf) && et.estFonction()){
                symbole = list.get(et) ;
            }
        }


        return symbole;
    }

    /**
     * Fonction qui retourne le type de retour de la fonction
     * @return
     */
    @Override
    public Type getType() {
        //On récupère le type de retour de la fonction
        return getSymbole().getType();
    }

    /**
     * Fonction estbool indique si expression est constantebool
     * @return
     */
    @Override
    public boolean estBool() {
        return false;
    }

    /**
     * Fonction estFonction indique si expression est fonction
     * @return
     */
    @Override
    public boolean estFonction() {
        return true;
    }

    /**
     * Getteur idf
     * @return
     */
    public String getIdf() {
        return idf;
    }

    @Override
    public boolean estConstante() {
        return false;
    }

    /**
     * Fonction toString retourne l'identifiant
     *
     * @return
     */
    @Override
    public String toString() {
        String param = listParam.get(0).toString();
        for( int i=1; i<listParam.size(); i++ ){
            param = param + "," +  listParam.get(i).toString();
        }
        return idf + "("+param+")";
    }
}
