package zoot.arbre.expressions;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        String ConstanteEntiere = cste;
                String code = "li $v0,"+ ConstanteEntiere+" #on stock constante entiere dans le registre v0\n"
                +"move $a0, $v0                #Copie de la valeur de v0 dans a0\n"
                +"li $v0, 1                    #v0 <-- 1 (code du print entier)\n"
                +"syscall                      #Afficher\n";
        return code;
        //throw new UnsupportedOperationException("fonction toMips non définie ") ;
    }

}

