package zoot.arbre.expressions;


import zoot.tds.*;

import java.util.*;

public class Idf extends Expression {

    protected String variable;
    private int noBloc;


    /**
     * Constructeur
     * @param texte
     * @param n
     */
    public Idf(String texte, int n, int num) {
        super(n,num);
        variable = texte;
        verifier();
        this.noBloc=num;


    }


    /**
     * Fonction verifier, permet de verifier les informations
     */
    @Override
    public void verifier() {
       //Symbole symbole = TDS.getInstance().identifier(new Entree(variable, noLigne));

    }

    @Override
    public boolean estBinaire() {
        return false;
    }


    /**
     * Fonction toMips, traduction en mips
     * @return le mips en string
     */
    @Override
    public String toMIPS() {
        String code="";
        ArrayList<HashMap<Entree, Symbole>> tds =  TDS.getInstance().getBlocs();
        int nbFonctions = TDS.getInstance().getCompteurNbFonctions();
                if (noBloc == 0) {//TODO:à tester
                    int deplacement = TDS.getInstance().getCompteurDeplace();
                    int deplacementTotal = deplacement - getSymbole().getDeplacement();
                    return "\tlw $v0," + deplacementTotal + "($s3)\n";
                } else {//au niveau de fonction
                    for (HashMap<Entree, Symbole> map : tds) {
                        for (Entree entree : map.keySet()) {
                            Symbole symbole = map.get(entree);
                            SymboleVariable symboleVariable = null;
                            if(entree.getNumBloc()!=0&& !TDS.getInstance().identifier(entree).estFonction()) {
                                symboleVariable = (SymboleVariable) TDS.getInstance().identifier(entree);
                                if (entree.getNumBloc() != 0 && symboleVariable.getNumVar() == 0) {// variable locale
                                    if (Objects.equals(entree.getIdf(), variable)) {
                                        int deplacement = TDS.getInstance().getCompteurDeplace();
                                        int deplacementParam = deplacement - symbole.getDeplacement();
                                        return "\tlw $v0," + deplacementParam + "($s7) \n";
                                    }
                                }
                                if(nbFonctions<2) {
                                    if (entree.getNumBloc() != 0 && symboleVariable.getNumVar() != 0) {//parametre
                                        if (Objects.equals(entree.getIdf(), variable)&&entree.getNumBloc()==noBloc) {
                                            int zoneParam = 12 + TDS.getInstance().getCompteParam() * 4;
                                            int deplacement = getSymbole().getDeplacement();
                                            int deplacementParam = zoneParam + deplacement;
                                            return "\tlw $v0," + deplacementParam + "($s7)\n"; //compteurparam incorrect à partir de 2 fonctions
                                        }
                                    }
                                }
                                else{
                                    if (entree.getNumBloc() != 0 && symboleVariable.getNumVar() != 0) {//parametre
                                        if (Objects.equals(entree.getIdf(), variable)&&entree.getNumBloc()==noBloc) {
                                            int numFonction = entree.getNumBloc()-1;//on enleve le main
                                            int zoneParam = 12 + TDS.getInstance().getNbParametres().get(numFonction) * 4;
                                            int deplacement = getSymbole().getDeplacement();
                                            int deplacementParam = zoneParam;
                                            if(numFonction==0) {
                                                zoneParam = 12 + TDS.getInstance().getNbParametres().get(numFonction) * 4;
                                                deplacementParam = zoneParam + deplacement;
                                            }
                                            return "\tlw $v0," + deplacementParam + "($s7) \n"; //compteurparam incorrect à partir de 2 fonctions
                                        }
                                        }
                                    }
                                }
                            }
                        }
                    }
                return code; //si aucun de tout ça on retourne rien à revoir?
        }


    /**
     * Getteur
     * @return le symbole
     */
    public Symbole getSymbole(){
        Symbole symbole = null;

        // On regarde deja dans le bloc de fonction
        HashMap<Entree,Symbole> list = TDS.getInstance().getBlocs().get(numBloc);
        for (Entree et : list.keySet()) {
            if(et.getIdf().equals(variable) && !et.estFonction()){
                symbole = list.get(et) ;
            }
        }
        // Puis dans le main
        if(symbole == null){

            HashMap<Entree,Symbole> list2 = TDS.getInstance().getBlocs().get(0);
            for (Entree et : list2.keySet()) {
                if(et.getIdf().equals(variable) && !et.estFonction()){
                    symbole = list2.get(et) ;
                }
            }
        }

        return symbole;

    }


    /**
     * Fonction toString retourne l'idf variable
     * @return
     */
    @Override
    public String toString() {
        return variable;
    }


    /**
     * Fonction qui retourne le type de la variable
     * @return
     */
    @Override
    public Type getType() {
        return getSymbole().getType();
    }


    /**
     * Getteur
     * @return booleen est une variable
     */
    public boolean estVariable(){
        return true;
    }


    /**
     * Fonction estbool indique si expression est constantebool
     * @return
     */
    @Override
    public boolean estBool() {
        return (getSymbole().getType().getType().equals("booleen"));
    }

    /**
     * Fonction estFonction indique si expression est fonction
     * @return
     */
    @Override
    public boolean estFonction() {
        return false;
    }

    /**
     * Getteur idf
     * @return
     */
    @Override
    public String getIdf() {
        return variable;
    }

    @Override
    public boolean estConstante() {
        return false;
    }

    @Override
    public int getNombreDePlaces() {
        return 0;
    }

}
