package zoot.test;

import org.junit.jupiter.api.Test;
import zoot.arbre.BlocDInstructions;


import static org.junit.jupiter.api.Assertions.*;


class BlocDInstructionsTest {

    @Test
    void toMIPS() {
        BlocDInstructions bloc = new BlocDInstructions(0);
        bloc.toMIPS();
        String expectedCode =  ".text\n\n"+
                "main :\n"
                +"\nend :\n" +
                "\tli $v0, 10\n" +
                "\tsyscall";
        assertEquals(expectedCode, bloc.toMIPS());
    }
}