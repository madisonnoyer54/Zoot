package zoot.arbre.expressions;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        String ConstanteEntiere = cste;
                String code = "\tli $v0,"+ ConstanteEntiere+"  # On stock constante entiere dans le registre v0\n";
        return code;
        //throw new UnsupportedOperationException("fonction toMips non définie ") ;
    }

}

