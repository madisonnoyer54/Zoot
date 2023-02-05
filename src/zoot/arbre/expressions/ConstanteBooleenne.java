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
         * Fonction qui indique si la constante est booléene
         * @return
         */
        public boolean estBool() {
                return true;
        }


        /**
         * Fonction toMips, traduction en mips si la constante vaut vrai on stock 1 sinon on stock 0
         * @return
         */
        @Override
        public String toMIPS() {
            return "\tli $v0, " + (this.cste.equals("vrai") ? "1" : "0") +"\n";
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
