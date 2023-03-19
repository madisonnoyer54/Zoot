package zoot.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.*;

import static org.junit.jupiter.api.Assertions.*;

class IdfTest {
    private Idf idf;

    @BeforeEach
    void setUp() {
       // Entree entree = new EntreeVariable("variable1",0);
        Symbole symbole = new SymboleVariable("entier");
      //  TDS.getInstance().getTableDesSymboles().put(entree,symbole);
        idf = new Idf("variable1", 1);
        assertNotNull(idf);
    }
    @Test
    void verifier() {
        try {
            idf.verifier();
            assertTrue(true);
        } catch (AnalyseSemantiqueException e) {
            fail("La méthode verifier() a levé une exception pour une variable valide");
        }

    }

    @Test
    void toMIPS() {
        assertNotNull(idf.toMIPS());
        assertEquals(idf.toMIPS(),"\tlw $v0,"+idf.getSymbole().getDeplacement() + "($s7)\n");
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