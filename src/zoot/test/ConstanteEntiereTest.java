package zoot.test;

import org.junit.jupiter.api.Test;
import zoot.arbre.expressions.ConstanteEntiere;

import static org.junit.jupiter.api.Assertions.*;

class ConstanteEntiereTest {

    @Test
    void toMIPS() {
        ConstanteEntiere constTest = new ConstanteEntiere("10", 0);
        String expectedCode = "\tli $v0,10  # On stock constante entiere dans le registre v0\n";
        assertEquals(expectedCode, constTest.toMIPS());
    }
}