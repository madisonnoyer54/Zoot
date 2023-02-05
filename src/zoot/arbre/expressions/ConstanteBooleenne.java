package zoot.arbre.expressions;

import zoot.tds.Type;

public class ConstanteBooleenne extends Constante {

        /**
         * Contructeur
         * @param texte
         * @param n
         */
        public ConstanteBooleenne(String texte, int n) {
            super(texte, n);
        }


        /**
         * Fonction toMips, traduction en mips
         * @return
         */
        @Override
        public String toMIPS() {
            return null;
        }

    /**
     * Getteur
     * @return type de la constante
     */
    public Type getType(){
        return Type.BOOLEEN;
    }

    /**
     * Getteur
     * @return booleen est une variable
     */
    public boolean estVariable(){
        return false;
    }
    }
