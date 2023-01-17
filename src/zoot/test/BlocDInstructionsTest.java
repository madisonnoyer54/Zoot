package zoot.test;

import org.junit.jupiter.api.Test;
import zoot.arbre.BlocDInstructions;
import zoot.arbre.instructions.Instruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;

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