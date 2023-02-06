package zoot.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoot.arbre.expressions.ConstanteBooleenne;
import zoot.arbre.expressions.ConstanteEntiere;

import static org.junit.jupiter.api.Assertions.*;

class ConstanteBooleenneTest {
    private ConstanteBooleenne constanteBooleenneV;
    private ConstanteBooleenne constanteBooleenneF;
    @BeforeEach
    void setUp() {
        this.constanteBooleenneV = new ConstanteBooleenne("vrai",1);
        this.constanteBooleenneF = new ConstanteBooleenne("faux",0);

    }

    @Test
    void estBool() {
        ConstanteEntiere entier = new ConstanteEntiere("lentier",8);
        assertTrue(constanteBooleenneV.estBool());
        assertTrue(constanteBooleenneF.estBool());
        assertFalse(entier.estBool());
    }

    @Test
    void toMIPS() {
        String code =constanteBooleenneV.toMIPS();
        String expectedCode = "\tli $v0, 1\n";
        assertEquals(code,expectedCode);
        code =constanteBooleenneF.toMIPS();
        expectedCode = "\tli $v0, 0\n";
        assertEquals(code,expectedCode);
    }

    @Test
    void getType() {
        assertEquals(constanteBooleenneV.getType().getType(),"booleen");
        assertEquals(constanteBooleenneF.getType().getType(),"booleen");

    }
}