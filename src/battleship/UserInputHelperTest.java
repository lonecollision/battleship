package battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInputHelperTest {
    private UserInputHelper ui;

    @BeforeEach
    void init() {
        ui = new UserInputHelper();
    }

    @Test
    @DisplayName("Testing menu input")
    void menuInputTest() {
        // Setup input stream
        System.setIn(new ByteArrayInputStream("5\n1000000000000000000000\n'u'\nasdka;sd\n;\n-10000\n0\n2".getBytes()));
        // Assertion
        assertEquals(2, ui.menuInput(4));
    }

    @Test
    @DisplayName("Testing board input")
    void boardInputTest() {
        // Setup input stream
        System.setIn(new ByteArrayInputStream("11\ntest string\n100000\n5000000000000000\n3".getBytes()));//CHANGE HERE
        // Assertion
        assertEquals(3, ui.boardInput(10, "X"));
    }

    @Test
    @DisplayName("Testing play again option input")
    void continueInput() {
        //Setup input stream
        System.setIn(new ByteArrayInputStream("1000000000000000000000\n50\n'u'\n0Y\nYn\nY\nN".getBytes()));
        //Assertion
        assertEquals("Y",ui.continueInput("Y", "N"));
    }
}