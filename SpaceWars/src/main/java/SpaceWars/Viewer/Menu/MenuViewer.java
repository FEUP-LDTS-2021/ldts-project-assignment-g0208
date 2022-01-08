package SpaceWars.Viewer.Menu;

import SpaceWars.Gui.FileReader;
import SpaceWars.Gui.GUI;
import SpaceWars.Viewer.Viewer;

import java.io.IOException;

public class MenuViewer extends Viewer<startMenu> {

    public MenuViewer(startMenu menu) {
        super(menu);
    }

    public void drawElements (GUI gui) throws IOException {
        gui.background();
        gui.drawName(new FileReader().readFile(("./src/main/resources/textFiles/menu/title.txt")), "#000000");
    }
}
