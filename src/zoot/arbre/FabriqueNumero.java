package zoot.arbre;

public class FabriqueNumero {
        private int nb;

        /**
         * Constructeur singleton FabriqueNumero.
         */
        private FabriqueNumero() {
            this.nb = 0;
        }

        private static final FabriqueNumero instance = new FabriqueNumero();

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
}
