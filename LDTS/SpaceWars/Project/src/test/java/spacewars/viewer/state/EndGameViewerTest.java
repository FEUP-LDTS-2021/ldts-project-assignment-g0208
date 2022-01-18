package spacewars.viewer.state;

import spacewars.gui.GUI;
import spacewars.gui.LanternaGUI;
import spacewars.model.Position;
import spacewars.model.element.button.Button;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class EndGameViewerTest {
    EndGameViewer endGameViewer;
    TextGraphics tg;
    List<Button> buttons;
    GUI gui;

    @BeforeEach
    void setUp() {
        this.tg = Mockito.mock(TextGraphics.class);
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        this.gui = new LanternaGUI(screen);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        List<String> colors = Collections.singletonList("#FFFFFF");
        this.buttons = Arrays.asList(new Button(new Position(1,2), null,colors), new Button(new Position(3,4),null,colors));
        this.endGameViewer = new EndGameViewer(gui,buttons,true);
    }

    @Test
    void draw() throws IOException {
        endGameViewer.draw();
        Mockito.verify(tg, Mockito.times(1)).fillRectangle(new TerminalPosition(6,10), new TerminalSize(15,5),' ');
        Mockito.verify(tg, Mockito.times(1)).putString(2,12,"YOU WON THE BATTLE");

    }
}