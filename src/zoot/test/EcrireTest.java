package zoot.test;

import zoot.arbre.expressions.ConstanteBooleenne;
import zoot.arbre.expressions.ConstanteEntiere;
import zoot.arbre.expressions.Expression;
import zoot.arbre.instructions.Ecrire;

import static org.junit.jupiter.api.Assertions.*;


class EcrireTest {

    @org.junit.jupiter.api.Test
    void testToMIPS() {
        Expression expTest = new ConstanteEntiere("7",7);
        Ecrire ecrire = new Ecrire(expTest, 0);
        String expectedCode = "# Ecrire "+expTest+"\n"+expTest.toMIPS()
                + "\tmove $a0, $v0  # Copie de la valeur de v0 dans a0\n"
                + "\tli $v0, 1\n"
                + "\tsyscall\n"
                +"# Ecrire un saut de ligne\n"+
                "\tla $a0, str # $a0 <- adresse de la chaîne à écrire\n" +
                "\tli $v0,4 # $v0 <- code du print\n" +
                "\tsyscall # afficher\n\n";
        assertEquals(expectedCode, ecrire.toMIPS());
        expTest = new ConstanteBooleenne("a",0);
        ecrire = new Ecrire(expTest, 0);
        expectedCode = "# Ecrire "+expTest+"\n"+expTest.toMIPS()
                + "\tmove $a0, $v0  # Copie de la valeur de v0 dans a0\n"
                + "\tli $v0, 1\n"
                + "\tsyscall\n"
                +"# Ecrire un saut de ligne\n"+
                "\tla $a0, str # $a0 <- adresse de la chaîne à écrire\n" +
                "\tli $v0,4 # $v0 <- code du print\n" +
                "\tsyscall # afficher\n\n";
        assertEquals(expectedCode, ecrire.toMIPS());
    }
}