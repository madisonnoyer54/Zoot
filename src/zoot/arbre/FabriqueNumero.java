package zoot.arbre;

public class FabriqueNumero {
        private int nb;
        private static final FabriqueNumero instance = new FabriqueNumero();

        /**
         * Constructeur singleton FabriqueNumero.
         */
        private FabriqueNumero() {
            this.nb = 0;
        }

        /**
         * Fonction qui retourne une instance de FabriqueNumero.
         *
         * @return une instance de la fabrique
         */
        public static FabriqueNumero getInstance() {
            return instance;
        }

        /**
         * Fonction qui génère un nombre
         * @return
         */
        public int genererNombre() {
                return nb++;
        }
        public void restNombre(){
                this.nb=1;
        }
}
