package SpaceWars.GUI;

import SpaceWars.Model.Menu.Button;
import SpaceWars.Model.Position;

import java.io.IOException;
import java.util.List;

public interface GUI {

    PressedKey getKeyInput() throws IOException;
    void drawButton(Button button);
    enum PressedKey {UP,DOWN, F, ENTER, ESCAPE, OTHER};
    void draw(Position position, List<String> appearance, String hexColor, String backgroundColor) throws IOException;
    void drawbackground();
    void drawTitle(List<String> Title, String hexColor);
    void refresh() throws IOException;
    void close() throws IOException;


}
