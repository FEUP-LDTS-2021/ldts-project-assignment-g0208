package SpaceWars.Gui;

import java.io.IOException;
import java.util.List;

public interface GUI {
    enum PressedKey {UP,DOWN,RIGHT,LEFT, SHOOT, OTHER,ESCAPE, ENTER};
    PressedKey getKeyInput() throws IOException;
    void background();
    void refresh() throws IOException;
    void close() throws IOException;

    /*
    * Menus
     */

    void drawMenu();
    void drawName(List<String> name, String hexColor);


}
