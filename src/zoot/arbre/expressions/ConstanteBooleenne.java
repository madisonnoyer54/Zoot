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

    public Type getType(){
        return Type.BOOLEEN;
    }
    }
