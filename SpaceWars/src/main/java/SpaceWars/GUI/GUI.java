package SpaceWars.GUI;

import SpaceWars.Model.Position;
import SpaceWars.Model.Menu.Button;

import java.io.IOException;
import java.util.List;

public interface GUI {
    enum PressedKey {UP,DOWN,RIGHT,LEFT,OTHER,ESCAPE, ENTER};
    PressedKey getKeyInput() throws IOException;
    void refresh() throws IOException;
    void close() throws IOException;


    /** Menu **/
    void drawMenuBackground();
    void drawTitle( List<String> title, String hexColor);
    void drawButton(Button button);
}
