package zoot.test;

import org.junit.jupiter.api.Test;
import zoot.arbre.FabriqueNumero;
import zoot.arbre.expressions.ConstanteBooleenne;
import zoot.arbre.expressions.ConstanteEntiere;
import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.arbre.instructions.Ecrire;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.TDS;

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
        StringBuilder code = new StringBuilder();
        FabriqueNumero.getInstance().restNombre();
        int numero = FabriqueNumero.getInstance().genererNombre();
        code.append("# Ecrire "+expTest+"\n"+expTest.toMIPS()
                + "\tmove $a0, $v0  # Copie de la valeur de v0 dans a0\n");
        code.append("\tbeq $zero, $a0, Sinon").append(numero).append("\n");
        code.append("\tla $a0, vraiAff\n");
        code.append("\tli $v0, 4\n");
        code.append("\tsyscall\n");
        code.append("\tb FinSi").append(numero).append("\n");
        code.append("\tSinon").append(numero).append(":").append("\n");
        code.append("\tla $a0, fauxAff\n");
        code.append("\tli $v0, 4\n");
        code.append("\tsyscall\n");
        code.append("\tFinSi").append(numero).append(":").append("\n");
        code.append("# Ecrire un saut de ligne\n"+
                "\tla $a0, str # $a0 <- adresse de la chaîne à écrire\n" +
                "\tli $v0,4 # $v0 <- code du print\n" +
                "\tsyscall # afficher\n\n");
        assertEquals(code.toString(), ecrire.toMIPS().toString());
    }

    @Test
    void verifier() {
        Entree entree = new Entree("var",0);
        Symbole symbole = new Symbole("entier");
        TDS.getInstance().getTableDesSymboles().put(entree,symbole);
        Idf idf = new Idf("var",1);
        idf.verifier();
        TDS.getInstance().getTableDesSymboles().remove(entree,symbole); //variable non déclarée
        Ecrire ecrire = new Ecrire(idf,0);
        assertThrows(AnalyseSemantiqueException.class,ecrire::verifier);
    }
}