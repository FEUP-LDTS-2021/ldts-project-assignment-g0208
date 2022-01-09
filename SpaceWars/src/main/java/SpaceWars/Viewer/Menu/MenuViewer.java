package SpaceWars.Viewer.Menu;

import SpaceWars.Model.Menu.StartMenu;
import SpaceWars.GUI.FileReader;
import SpaceWars.GUI.GUI;
import SpaceWars.Model.Menu.StartMenu;
import SpaceWars.Viewer.Viewer;

import java.io.IOException;

public class MenuViewer extends Viewer<StartMenu> {

    public MenuViewer (StartMenu menu) {
        super(menu);
    }

    public void drawElements(GUI gui) throws IOException {
        gui.drawbackground();
        gui.drawTitle(new FileReader().readFile("./src/main/resources/textFiles/SpaceWars.txt"), "#FFFFFF");
        for(int i=0; i < getModel().getNumberOptions(); i++) {
            gui.drawButton(getModel().getOptions().get(i));
        }
    }

}
