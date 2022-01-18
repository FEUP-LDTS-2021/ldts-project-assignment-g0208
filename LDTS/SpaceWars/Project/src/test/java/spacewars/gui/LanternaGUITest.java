package spacewars.gui;

import spacewars.model.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.mockito.Mockito;

class LanternaGUITest {
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        this.tg = Mockito.mock(TextGraphics.class);
        TerminalScreen screen = Mockito.mock(TerminalScreen.class);
        this.gui = new LanternaGUI(screen);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
    }

    @Test
    void drawBullet() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawBullet(position,color,".");

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), ".");
    }

    @Test
    void drawPlayer() {
        Position position = new Position(2,2);
        String color = "#000000";
        gui.drawPlayer(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "m");
    }

    @Test
    void drawButton() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawButton(position,position,"buttontest",color,color,10,10);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "buttontest");
    }

    @Test
    void drawTF() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawTF(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "q");
    }

    @Test
    void drawSD() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawSD(position,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "o");
    }

    @Test
    void drawDS() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawDS(position,color);
        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "p");
    }

    @Test
    void drawTitle() {
        Position position = new Position(3,5);
        String color = "#000000";
        gui.drawTitle(position,"stringtest",color,color);

        Mockito.verify(tg, Mockito.times(1)).putString(position.getX(), position.getY(), "stringtest");
    }


    @Test
    void drawInfo() {
        gui.drawInfo(10);
        Mockito.verify(tg, Mockito.times(1)).putString(0, gui.getHeight(), String.valueOf(10));
    }

    @Test
    void drawRectangle() {
        Position position = new Position(3,5);
        gui.drawRectangle(tg,"#FFFFFF",20,20,position);

        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }

    @Test
    void drawBackground() {
        Position position = new Position(3,5);
        gui.drawRectangle(tg,"#FFFFFF",20,20,position);

        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }
}