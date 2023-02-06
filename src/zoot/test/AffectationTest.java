package zoot.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoot.arbre.expressions.ConstanteEntiere;
import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.arbre.instructions.Affectation;
import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;

import static org.junit.jupiter.api.Assertions.*;

class AffectationTest {
    private Idf idf;
    private Affectation affect;
    private Expression express;
    private Symbole symbole;
    private Entree entree;
    @BeforeEach
    void setUp() {
        entree = new Entree("var",0);
        symbole = new Symbole("entier");
        TDS.getInstance().getTableDesSymboles().put(entree,symbole);
        express = new ConstanteEntiere("nb",7);
        idf = new Idf("var",7);
        affect = new Affectation(idf.toString(),express,7);
    }

    @Test
    void verifier() {
    }

    @Test
    void toMIPS() {
        String code = "# Affectation (" + idf.toString() +" = "+ express.toString()+")\n"+
                express.toMIPS()+
                "\tsw $v0, "+ idf.getSymbole().getDeplacement()+"($s7)" +"\n\n";
        assertEquals(code,affect.toMIPS());

    }
}