package SpaceWars.Gui;

import org.junit.jupiter.api.*;

import java.io.IOException;

public class LanternGUITest {
    private LanternGui lanternGUI;

    @BeforeEach
    void setUp() throws IOException {
        lanternGUI = new LanternGui();
    }

    @AfterEach
    void cleanUp() throws IOException {
        lanternGUI.close();
        lanternGUI = null;
    }


    @Test
    void fill() throws IOException {
        lanternGUI.fill("#ffff00");
    }
}
