package zoot.test;

import org.junit.jupiter.api.Test;
import zoot.arbre.BlocDInstructions;


import static org.junit.jupiter.api.Assertions.*;


class BlocDInstructionsTest {

    @Test
    void toMIPS() {
        BlocDInstructions bloc = new BlocDInstructions(0);
        bloc.toMIPS();
        String expectedCode =  ".data\n" +
                "str: 	.asciiz \"\\n\"\n" +
                "vrai:	.word 1\n" +
                "faux:	.word 0\n" +
                "vraiAff:	.asciiz \"vrai\"\n" +
                "fauxAff:	.asciiz \"faux\"\n" +
                ".text\n\n" +
                "main :\n\n" +
                "# Initialiser $s7 avec $sp\n" +
                "\tmove $s7,$sp\n" +
                "# Reserve la place des variables\n" +
                "\taddi $sp,$sp,0\n\n" +
                "\nend :\n" +
                "\tli $v0, 10\n" +
                "\tsyscall";
        assertEquals(expectedCode, bloc.toMIPS());
    }
}