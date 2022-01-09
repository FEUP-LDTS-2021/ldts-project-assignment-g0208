package SpaceWars.GUI;

import SpaceWars.Model.Position;

import java.io.IOException;
import java.util.List;

public interface GUI {

    enum PressedKey {UP,DOWN, F, ESCAPE};
    void draw(Position position, List<String> appearance, String hexColor, String backgroundColor) throws IOException;
    void drawbackground();
    void drawElement();
    void refresh() throws IOException;
    void close() throws IOException;


}
