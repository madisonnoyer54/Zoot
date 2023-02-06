package zoot.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.AnalyseVariableNomDejaPris;
import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;
import zoot.tds.Type;

import static org.junit.jupiter.api.Assertions.*;

class IdfTest {
    private Idf idf;

    @BeforeEach
    void setUp() {
        Entree entree = new Entree("variable1");
        Symbole symbole = new Symbole("entier");
        TDS.getInstance().getTableDesSymboles().put(entree,symbole);
        idf = new Idf("variable1", 1);
        assertNotNull(idf);
    }
    @Test
    void verifier() {
        try {
            idf.verifier();
            assertTrue(true);
        } catch (AnalyseVariableNomDejaPris e) {
            fail("La méthode verifier() a levé une exception pour une variable valide");
        }

    }

    @Test
    void toMIPS() {
        assertNotNull(idf.toMIPS());
    }

    @Test
    void getSymbole() {
        assertNotNull(idf.getSymbole());
        assertTrue(idf.getSymbole().getType().getType().equals("entier"));
    }

    @Test
    void estBool() {
        assertFalse(idf.estBool());
    }
}