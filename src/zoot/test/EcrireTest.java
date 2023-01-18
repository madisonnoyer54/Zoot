package zoot.test;

import zoot.arbre.expressions.ConstanteEntiere;
import zoot.arbre.expressions.Expression;
import zoot.arbre.instructions.Ecrire;

import static org.junit.jupiter.api.Assertions.*;


class EcrireTest {

    @org.junit.jupiter.api.Test
    void testToMIPS() {
        Expression expTest = new ConstanteEntiere("7",7);
        Ecrire ecrire = new Ecrire(expTest, 0);
        String expectedCode = expTest.toMIPS()
                + "\tmove $a0, $v0  # Copie de la valeur de v0 dans a0\n"
                + "\tli $v0, 1  # v0 <-- 1 (code du print)\n"
                + "\tsyscall  # Afficher\n\n";
        assertEquals(expectedCode, ecrire.toMIPS());
    }
}