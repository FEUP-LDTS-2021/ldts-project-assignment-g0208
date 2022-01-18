package spacewars.viewer.state;

import spacewars.gui.GUI;
import spacewars.gui.LanternaGUI;
import spacewars.model.Position;
import spacewars.model.element.Player;
import spacewars.model.element.button.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PlayingViewerTest {
    PlayingViewer playingViewer;
    Player player;
    List<Button> buttons;
    GUI gui;

    @BeforeEach
    void setUp() {
        List<String> colors = Collections.singletonList("#FFFFFF");
        this.buttons = Arrays.asList(new Button(new Position(1,2), null,colors), new Button(new Position(3,4),null,colors));
        this.gui = Mockito.mock(LanternaGUI.class);
        this.player = new Player(new Position(3,3),colors,null,10);
        this.playingViewer = new PlayingViewer(gui,buttons,player);
    }
    @Test
    void draw() throws IOException {
        playingViewer.draw();

        Mockito.verify(gui, Mockito.times(1)).drawInfo(player.getEnergy());
    }
}
