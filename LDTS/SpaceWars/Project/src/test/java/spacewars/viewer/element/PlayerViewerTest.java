package spacewars.viewer.element;

import spacewars.gui.GUI;
import spacewars.model.Position;
import spacewars.model.element.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

class PlayerViewerTest {

    Player player;
    PlayerViewer viewer;
    GUI gui;

    @BeforeEach
    void setUp() {
        this.player = new Player(new Position(4,4), Arrays.asList("#FFFFFF"),null,10);
        viewer = new PlayerViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() {
        viewer.drawElement(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPlayer(player.getPosition(),player.getColor());
    }
}