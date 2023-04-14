package zoot.arbre.expressions;

import zoot.tds.Type;

import java.util.List;

public class ConstanteBooleenne extends Constante {

        /**
         * Contructeur
         * @param texte
         * @param n
         */
        public ConstanteBooleenne(String texte, int n, int num) {
            super(texte, n, num);
        }


        /**
         * Fonction qui indique si la constante est booléene
         * @return vrai si la constante est booleene, faux sinon
         */
        public boolean estBool() {
                return true;
        }

        @Override
        public boolean estFonction() {
                return false;
        }

        @Override
        public int getNombreDePlaces() {
                return 0;
        }

        /**
         * Fonction toMips, traduction en mips si la constante vaut vrai on stock 1 sinon on stock 0
         * @return le code mips en string
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
